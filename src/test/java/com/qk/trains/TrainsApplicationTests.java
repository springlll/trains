package com.qk.trains;

import com.qk.trains.entity.LightData;
import com.qk.trains.entity.PressureData;
import com.qk.trains.mapper.PressureDataMapper;
import com.qk.trains.service.LightDataService;
import com.qk.trains.service.SocketDataService;
import com.qk.trains.socket.ReceiveServer;
import com.qk.trains.util.SystemClock;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrainsApplicationTests {

	@Autowired
	LightDataService lightDataService;

	@Autowired
	PressureDataMapper pressureDataMapper;

	@Test
	public void contextLoads() {
		List<LightData> list = lightDataService.getList();
		List<LightData> list2 = new ArrayList<>();
		long start = SystemClock.now();
		int count = 0;
		for (int i = 0; i < 100; i++) {
			for (LightData ld : list) {
				list2.add(ld);
				if (ld.getVoltage() == 0.03) {
					LightData ld2 = ld;
				}
				count++;
			}
		}
		for(LightData ld :list2){
			LightData ld2 = ld;
			ld2.getVoltage();
			ld2.getRaw();
		}
		System.out.println(SystemClock.now() - start);
		System.out.println(count);
	}
}
