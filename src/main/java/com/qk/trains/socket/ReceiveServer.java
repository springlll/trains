package com.qk.trains.socket;

import org.apache.commons.lang3.ArrayUtils;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

/**
 * Socket接收服务端
 */
public class ReceiveServer {
	/**
	 * 首尾符号信息接收模式
	 */
	public final static int FIXED_MESSAGE = 0;

	/**
	 * 长度信息接收
	 */
	public final static int LENGTH_MESSAGE = 10;

	/**
	 * ServerSocket 实例
	 */
	private ServerSocket serverSocket;

	/**
	 * 连接列表
	 */
	private List<Thread> socketThreads;

	/**
	 * 端口
	 */
	private int port;

	/**
	 * 信息确定长度
	 */
	private int timeout;

	/**
	 * 线程无数据后的等待时间
	 */
	private int waitingTime;

	/**
	 * 在线状态
	 */
	private boolean online;

	/**
	 * 线程索引起始值，累加
	 */
	private long threadIdCounter;

	/**
	 * 发送至指定Socket连接的数据
	 */
	private HashMap<Long, byte[]> sendingDatas;

	/**
	 * 信息接收模式，默认是首尾模式
	 */
	private int receiveMode = FIXED_MESSAGE;

	/**
	 * 首尾符号信息接收监听器结构
	 */
	private List<FixedListenerStructure> fixedListeners;

	/**
	 * 长度信息接收监听器结构
	 */
	private LengthListenerStructure lengthListener;

	/**
	 * 构造函数，初始化集合
	 *
	 * @param port        启动端口
	 * @param timeout     连接超时时长
	 * @param waitingTime 线程无数据时的休眠时间，同时影响数据发送等待的时间
	 */
	public ReceiveServer(int port, int timeout, int waitingTime) {
		this.port = port;
		this.timeout = timeout;
		this.waitingTime = waitingTime;
		this.threadIdCounter = 100;
		socketThreads = new ArrayList<>();
		sendingDatas = new HashMap<>();
		fixedListeners = new ArrayList<>();
	}

	/**
	 * 添加长度接收监听器
	 *
	 * @param length   限定长度，当到达长度结束信息
	 * @param listener 监听器
	 */
	public void setLengthMessageReceiveListener(int length, MessageReceiveListener listener) {
		lengthListener = new LengthListenerStructure(length, listener);
	}

	/**
	 * 添加首尾符号接收监听器，相同开头字节的监听器会被覆盖
	 *
	 * @param prefix   信息开头字节
	 * @param suffix   信息结束字节
	 * @param listener 监听器
	 */
	public void addFixedMessageReceiveListener(byte[] prefix, byte[] suffix, MessageReceiveListener listener) {
		for (FixedListenerStructure l : fixedListeners) {
			if (l.getPrefix() == prefix) {
				fixedListeners.remove(l);
			}
		}
		fixedListeners.add(new FixedListenerStructure(prefix, suffix, listener));
	}

	/**
	 * 根据前缀字符移除接收监听器
	 *
	 * @param prefix 前缀字符
	 */
	public void removeMessageReceiveListener(byte[] prefix) {
		for (FixedListenerStructure l : fixedListeners) {
			if (l.getPrefix() == prefix) {
				fixedListeners.remove(l);
			}
		}
	}

	/**
	 * 移除所有接收监听器
	 */
	public void removeAllMessageReceiveListener() {
		fixedListeners.clear();
		lengthListener = null;
	}

	/**
	 * 设置信息接收模式
	 *
	 * @param mode 模式
	 */
	public void setMesageReceiveMode(int mode) {
		receiveMode = mode;
	}

	/**
	 * 设置服务端在线状态
	 *
	 * @throws IOException ServerSocket 初始化错误，请检查端口占用情况
	 */
	public void setOnline() throws IOException {
		if (online) {
			return;
		}
		serverSocket = new ServerSocket(port);
		online = true;
		//服务端循环接收的线程
		Thread acceptThread = new Thread(() -> {
			try {
				while (online) {
					Socket subSocket = serverSocket.accept();
					threadIdCounter++;
					Thread thread = new Thread(new ReciveSocket(subSocket, threadIdCounter));
					socketThreads.add(thread);
					thread.start();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		acceptThread.start();
	}

	/**
	 * 关闭服务端
	 */
	public void setOffline() {
		online = false;
		//开启一个新socket使服务端accept不再阻塞
		new Thread(() -> {
			try {
				Thread.sleep(waitingTime + 200);
				Socket socket = new Socket(serverSocket.getInetAddress(), port);
				socket.close();
				if (!serverSocket.isClosed()) {
					serverSocket.close();
				}
				System.out.println("Make A Self Connection Close Accept...");
			} catch (IOException | InterruptedException e) {
				e.printStackTrace();
			}
		}).start();
	}

	/**
	 * 提交要发送的数据到缓存
	 *
	 * @param threadId 线程ID
	 * @param data     发送的数据
	 */
	public void submitSocketMessage(long threadId, byte[] data) {
		sendingDatas.put(threadId, data);
	}

	/**
	 * 与每一个客户端的连接线程
	 */
	public class ReciveSocket implements Runnable {
		/**
		 * Socket实例
		 */
		private Socket subSocket;

		/**
		 * 超时计数
		 */
		private int timeoutCount;

		/**
		 * 线程ID
		 */
		private long id;

		/**
		 *
		 */
		private Map<String, Object> attributes;

		/**
		 * 构造函数
		 *
		 * @param socket 传入的已连接的Socket
		 */
		public ReciveSocket(Socket socket, long id) {
			subSocket = socket;
			this.id = id;
		}

		/**
		 * 长连接循环
		 */
		@Override
		public void run() {
			attributes = new HashMap<>();
			try {
				System.out.println(subSocket.getInetAddress().getHostAddress() + ":" + subSocket.getPort() + " Connected...");
				BufferedInputStream br = new BufferedInputStream(subSocket.getInputStream());
				BufferedOutputStream bos = new BufferedOutputStream(subSocket.getOutputStream());
				List<Byte> buffer = new ArrayList<>();
				//信息接收在线循环
				while (online && !subSocket.isClosed()) {
					if (br.available() > 0) {
						timeoutCount = 0;
						byte[] bs = new byte[br.available()];
						br.read(bs);
						buffer.addAll(Arrays.asList(ArrayUtils.toObject(bs)));
					}
					FixedListenerStructure listenerStructure = null;
					List<Byte> data = new ArrayList<>();
					int end = 0;
					switch (receiveMode) {
						case FIXED_MESSAGE: //标识符截取信息
							int suffixIndex = 0;
							int searchIndex = 0;
							int fixedStartAt = 0;
							List<FixedListenerStructure> structures = fixedListeners;
							for (int cursor = 0; cursor < buffer.size(); cursor++) {
								byte read = buffer.get(cursor);
								if (listenerStructure == null) {
									structures = filterListener(structures, read, searchIndex);
									searchIndex++;
									if (structures.size() == 1) {
										listenerStructure = structures.get(0);
										fixedStartAt = cursor - searchIndex;
										cursor = -1;
										continue;
									} else if (structures.size() == 0) {
										break;
									}
								}
								if (listenerStructure != null && cursor >= fixedStartAt) {
									data.add(read);
									//匹配结尾符
									byte[] suffix = listenerStructure.suffix;
									if (suffix.length - suffixIndex <= buffer.size() - cursor) {
										if (read == suffix[suffixIndex]) {
											suffixIndex++;
										} else {
											suffixIndex = 0;
										}
										if (suffixIndex == suffix.length) {
											listenerStructure.getReceiveListener().onReceive(ArrayUtils.toPrimitive(data.toArray(new Byte[0])), id, attributes);
											data.clear();
											listenerStructure = null;
											end = cursor + 1;
											suffixIndex = 0;
										}
									}
								}
							}
							break;
						case LENGTH_MESSAGE: //长度信息截取
							int flag = 0;
							for (int cursor = 0; cursor < buffer.size(); cursor++) {
								byte read = buffer.get(cursor);
								if (read != 0x0 && flag == 0) {
									data.add(read);
									flag = 1;
								} else if (flag > 0) {
									data.add(read);
									flag++;
									if (flag == lengthListener.length) {
										lengthListener.getReceiveListener().onReceive(ArrayUtils.toPrimitive(data.toArray(new Byte[0])), id, attributes);
										data.clear();
										end = cursor + 1;
										flag = 0;
									}
								}
							}
							break;
					}
					//多出没结尾的数据保留到缓存
					List<Byte> newBuffer = new ArrayList<>();
					for (int b = end; b < buffer.size(); b++) {
						newBuffer.add(buffer.get(b));
					}
					buffer = newBuffer;
					//向客户端发送缓存的数据
					Iterator<Long> i = sendingDatas.keySet().iterator();
					while (i.hasNext()) {
						if (i.next() == id) {
							bos.write(sendingDatas.get(id));
							bos.flush();
							sendingDatas.remove(id);
						}
					}
					//超时跳出，关闭线程
					if (timeoutCount > timeout) {
						break;
					}
					//无数据时进入等待
					timeoutCount += waitingTime;
					Thread.sleep(waitingTime);
				}
				br.close();
				bos.close();
				subSocket.close();
			} catch (IOException | InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(subSocket.getInetAddress().getHostAddress() + ":" + subSocket.getPort() + " Disconnected...");
		}

		private List<FixedListenerStructure> filterListener(List<FixedListenerStructure> last, byte read, int index) {
			List<FixedListenerStructure> newList = new ArrayList<>();
			for (FixedListenerStructure fls : last) {
				try {
					if (fls.getPrefix()[index] == read) {
						newList.add(fls);
					}
				} catch (ArrayIndexOutOfBoundsException e) {
				}
			}
			return newList;
		}
	}

	/**
	 * 首尾符号监听器结构
	 */
	public class FixedListenerStructure {
		/**
		 * 信息开始字节
		 */
		private byte[] prefix;

		/**
		 * 信息结束字节串
		 */
		private byte[] suffix;

		/**
		 * 信息接收监听器
		 */
		private MessageReceiveListener receiveListener;

		//getter setter
		public byte[] getPrefix() {
			return prefix;
		}

		public void setPrefix(byte[] prefix) {
			this.prefix = prefix;
		}

		public byte[] getSuffix() {
			return suffix;
		}

		public void setSuffix(byte[] suffix) {
			this.suffix = suffix;
		}

		public MessageReceiveListener getReceiveListener() {
			return receiveListener;
		}

		public void setReceiveListener(MessageReceiveListener receiveListener) {
			this.receiveListener = receiveListener;
		}

		public FixedListenerStructure(byte[] prefix, byte[] suffix, MessageReceiveListener receiveListener) {
			this.prefix = prefix;
			this.suffix = suffix;
			this.receiveListener = receiveListener;
		}
	}

	/**
	 * 长度监听器结构
	 */
	public class LengthListenerStructure {
		private int length;

		/**
		 * 信息接收监听器
		 */
		private MessageReceiveListener receiveListener;

		//getter setter
		public int getLength() {
			return length;
		}

		public void setLength(int length) {
			this.length = length;
		}

		public MessageReceiveListener getReceiveListener() {
			return receiveListener;
		}

		public void setReceiveListener(MessageReceiveListener receiveListener) {
			this.receiveListener = receiveListener;
		}

		public LengthListenerStructure(int length, MessageReceiveListener receiveListener) {
			this.length = length;
			this.receiveListener = receiveListener;
		}
	}

	/**
	 * 信息完成一次接收的监听器
	 */
	public interface MessageReceiveListener {
		/**
		 * @param data 接收的数据
		 * @param id   线程ID
		 */
		public void onReceive(byte[] data, long id, Map<String, Object> attrs);
	}
}
