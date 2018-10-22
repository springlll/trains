package com.qk.trains.service;

import com.qk.trains.component.DeploymentProperties;
import com.qk.trains.entity.LightData;
import com.qk.trains.entity.PressureData;
import com.qk.trains.entity.TrainActivity;
import com.qk.trains.mapper.LightActivityMapper;
import com.qk.trains.mapper.LightDataMapper;
import com.qk.trains.mapper.PressureDataMapper;
import com.qk.trains.socket.ReceiveServer;
import com.qk.trains.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: trains
 * @description: Socket数据保存服务
 * @author: Xiaotian
 * @create: 2018-08-21 11:00
 **/
@Service
public class SocketDataService {

	@Autowired
	DeploymentProperties deploymentProperties;

	@Autowired
	TrainActivityService trainActivityService;

	@Autowired
	LightDataMapper lightDataMapper;

	@Autowired
	LightActivityMapper lightActivityMapper;

	@Autowired
	PressureDataMapper pressureDataMapper;

	private ReceiveServer pressureReceiveServer;

	private ReceiveServer lightDataReceiveServer;

	private List<TrainActivity> cacheTrainActivity = new ArrayList<>();

	/**
	 * 保存信号灯数据
	 *
	 * @param raw 原始字节
	 */
	public LightData addLightData(byte[] raw) {
		if (raw.length == 50) {
			try {
				ByteArrayInputStream bais = new ByteArrayInputStream(raw);
				if (bais.read() == 0x54) {
					bais.read();
					LightData ldata = new LightData();
					ldata.setRaw(new String(raw));
					//ldata.setSaveTime(SystemClock.now());
					ldata.setImei10(DataParseUtil.getString(bais, 10));
					ldata.setTrack(DataParseUtil.getInt(bais, 2));
					ldata.setDirection(DataParseUtil.getInt(bais, 2));
					ldata.setSendTime(DataParseUtil.getDate(bais));
					ldata.setCardId(DataParseUtil.getString(bais, 8));
					ldata.setLightSignal(DataParseUtil.getInt(bais, 1));
					ldata.setVoltage(DataParseUtil.getDouble(bais, 4));
					if (bais.read() != 0x56) {
						throw new NumberFormatException("Voltage unit not got 'Volt'");
					}
					ldata.setSignalValue(DataParseUtil.getInt(bais, 2));
					ldata.setAcceleration(DataParseUtil.getInt(bais, 1));
					ldata.setMachineNumber(DataParseUtil.getString(bais, 4));
					if (ldata.getVoltage() == StaticStatus.IN_PLACE_VOLTAGE) {
						ldata.setDataType(StaticStatus.IN_PLACE_SYMBOL);
					} else if (ldata.getVoltage() == StaticStatus.UP_SIGNAL_VOLTAGE) {
						ldata.setDataType(StaticStatus.UP_SIGNAL_SYMBOL);
					} else if (ldata.getVoltage() == StaticStatus.DOWN_SIGNAL_VOLTAGE) {
						ldata.setDataType(StaticStatus.DOWN_SIGNAL_SYMBOL);
					} else if (ldata.getVoltage() == StaticStatus.END_VOLTAGE) {
						ldata.setDataType(StaticStatus.END_SYMBOL);
					} else if (ldata.getVoltage() == StaticStatus.CHECK_TIME_VOLTAGE) {
						ldata.setDataType(StaticStatus.CHECK_TIME_SYMBOL);
					} else {
						ldata.setDataType(StaticStatus.NORMAL_DATA);
					}
					lightDataMapper.add(ldata);
					System.out.println(new String(raw) + " - Data OK!"); //sout
					return ldata;
				}
			} catch (NumberFormatException | IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Data illegal"); //sout
		return null;
	}

	/**
	 * 保存试风压力数据
	 *
	 * @param raw 原始字节
	 */
	public PressureData addPressureData(byte[] raw) {
		if (raw.length == 34) {
			try {
				ByteArrayInputStream bais = new ByteArrayInputStream(raw);
				PressureData pdata = new PressureData();
				pdata.setRaw(new String(raw));
				//pdata.setSaveTime(SystemClock.now());
				pdata.setMachineNumber(DataParseUtil.getString(bais, 5));
				pdata.setSendTime(DataParseUtil.getDate(bais));
				pdata.setTrack(DataParseUtil.getInt(bais, 2));
				//5个减号
				DataParseUtil.getString(bais, 5);
				pdata.setPressure(DataParseUtil.getInt(bais, 3));
				pdata.setVoltage(DataParseUtil.getDouble(bais, 4));
				if (bais.read() != 0x56) {
					throw new NumberFormatException("Voltage unit not got 'Volt'");
				}
				pdata.setCrc(DataParseUtil.getString(bais, 2));
				if (pdata.getPressure() == StaticStatus.PRESSURE_END_SYMBOL && pdata.getVoltage() == StaticStatus.PRESSURE_END_VOLTAGE) {
					pdata.setDataType(StaticStatus.END_SYMBOL);
				} else {
					pdata.setDataType(StaticStatus.NORMAL_DATA);
				}
				if (CheckUtil.CRC_4(pdata.getRaw().substring(0, 32)).equals(pdata.getCrc())) {
					pressureDataMapper.add(pdata);
					System.out.println(new String(raw) + " - Data OK!"); //sout
					return pdata;
				}
			} catch (NumberFormatException | IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Data illegal"); //sout
		return null;
	}

	/**
	 * 开启Socket服务器
	 */
	public void startServer() {
		ContextInstances.setAttribute("activities", new ArrayList<TrainActivity>());
		//实例化服务端
		pressureReceiveServer = new ReceiveServer(deploymentProperties.getLightListeningPort(), 60000, 500);
		lightDataReceiveServer = new ReceiveServer(deploymentProperties.getPressureListeningPort(), 60000, 200);
		//添加监听器
		pressureReceiveServer.setMesageReceiveMode(ReceiveServer.LENGTH_MESSAGE);
		pressureReceiveServer.setLengthMessageReceiveListener(34, (data, id, attrs) -> {
			System.out.println(new String(data) + "  Message Get By Thread:" + id); //sout
			PressureData pd = addPressureData(data);
			trainActivityService.updatePressure(pd);
		});
		lightDataReceiveServer.addFixedMessageReceiveListener("T".getBytes(), "W".getBytes(), (data, id, attrs) -> {
			System.out.println(new String(data) + "  Message Get By Thread:" + id); //sout
			LightData ld = addLightData(data);
			trainActivityService.updateLight(ld);
			double v = ld.getVoltage();
			if (v == 0.00) {
				System.out.println("Up Signal Received, Send 'AT+RECEIVED'");
				lightDataReceiveServer.submitSocketMessage(id, "AT+RECEIVED\r\n".getBytes());
			} else if (v == 0.01) {
				System.out.println("Data End Received, Send 'AT+RECEIVED'");
				lightDataReceiveServer.submitSocketMessage(id, "AT+RECEIVED\r\n".getBytes());
			} else if (v == 0.02) {
				System.out.println("In Place Received, Send 'AT+RECEIVED'");
				lightDataReceiveServer.submitSocketMessage(id, "AT+RECEIVED\r\n".getBytes());
			} else if (v == 0.04) {
				System.out.println("Down Signal Received, Send 'AT+RECEIVED'");
				lightDataReceiveServer.submitSocketMessage(id, "AT+RECEIVED\r\n".getBytes());
			} else {
				if (v == 0.03) {
					String date = Format.date("yy,MM,dd,HH,mm,ss");
					System.out.println("Check Time Request Received, Send '#AT+TIME=" + date + "'");
					lightDataReceiveServer.submitSocketMessage(id, ("#AT+TIME=" + date + "\r\n").getBytes());
				}
			}
			attrs.put("track", ld.getTrack());
			attrs.put("direction", ld.getDirection());
			attrs.put("machineNumber", ld.getMachineNumber());
		});
		lightDataReceiveServer.addFixedMessageReceiveListener("START\r\n".getBytes(), "FINISH\r\n".getBytes(), (data, id, attrs) -> {
			Integer track = (Integer) attrs.get("track");
			Integer direction = (Integer) attrs.get("direction");
			String machineNumber = (String) attrs.get("machineNumber");
			String imageFileName = machineNumber + "-" + track + "-" + direction + "-" + Format.date("yyMMddHHmmss") + ".jpg";
			if (track != null && direction != null && machineNumber != null) {
				try {
					File file = new File(deploymentProperties.getImageSavePath() + imageFileName);
					if (!file.getParentFile().exists()) {
						file.getParentFile().mkdirs();
					}
					BufferedOutputStream fos = new BufferedOutputStream(new FileOutputStream(file));
					fos.write(data, 7, data.length - 15);
					fos.close();
					trainActivityService.updateLightImage(machineNumber, track, direction, imageFileName);
				} catch (IOException e) {
					e.printStackTrace();
				}
				System.out.println("Image Size " + data.length + "byte Thread:" + id);
			} else {
				System.out.println("Unknow Device Image - Thread:" + id);
			}
		});
		lightDataReceiveServer.addFixedMessageReceiveListener("SET".getBytes(), "!\r\n".getBytes(), (data, id, attrs) -> {
			System.out.println("Received '" + new String(data).replace("\r\n", "") + "'");
		});
		lightDataReceiveServer.addFixedMessageReceiveListener(new byte[]{(byte) 254}, new byte[]{(byte) 254}, (data, id, attrs) -> {
			System.out.println("Heart Beat Package Received - Thread:" + id);
		});
		//设置在线
		try {
			pressureReceiveServer.setOnline();
			lightDataReceiveServer.setOnline();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 关闭Socket服务器
	 */
	public void stopServer() {
		pressureReceiveServer.setOffline();
		lightDataReceiveServer.setOffline();
	}
}
