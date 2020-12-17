package optimization.nio;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.lang.reflect.Field;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class NIOTest {
	public static void main(String[] args) throws Exception {
		// String resource = "f:/temp/resource.txt";
		// String destination = "f:/temp/destination.txt";
		// nioCopyFile(resource, destination);
		//
		// // position、limit、capacity
		// bufferConcept();
		//
		// // mark()用于记录当前位置，reset()函数用于恢复到mark所在的位置
		// markAndReset();

		// 复制缓冲区
		// copyBuffer();

		// 缓冲区分片
		// bufferShard();

		// 只读缓冲区
		// readOnlyBuffer();

		// 文件映射到内存
		// fileMapToRAM();

		// 处理结构化数据
		// dealStructuralData_Read();

		// 传统的基于流的I/O的操作和基于Buffer的I/O操作在性能上的差异
		// compare();

		// 监控DirectBuffer的使用情况
		monDirectBuffer();
	}

	// 监控DirectBuffer的使用情况
	public static void monDirectBuffer() throws Exception {
		Class<?> c = Class.forName("java.nio.Bits");
		Field maxMemory = c.getDeclaredField("maxMemory");
		maxMemory.setAccessible(true);
		Field reservedMemory = c.getDeclaredField("reservedMemory");
		reservedMemory.setAccessible(true);
		synchronized (c) {
			Long maxMemoryValue = (Long) maxMemory.get(null);// 总大小
			Integer reservedMemoryValue = (Integer) reservedMemory.get(null);// 剩余大小
			System.out.println("maxMemoryValue: " + maxMemoryValue);
			System.out.println("reservedMemoryValue: " + reservedMemoryValue);

		}
	}

	// 传统的基于流的I/O的操作和基于Buffer的I/O操作在性能上的差异
	public static void compare() throws Exception {
		int numofints = 4000000;
		DataOutputStream dos = new DataOutputStream(
				new BufferedOutputStream(new FileOutputStream(new File("f:/temp/temp_stream.tmp"))));
		for (int i = 0; i < numofints; i++)
			dos.writeInt(i);
		if (dos != null)
			dos.close();
	}

	// 处理结构化数据.通过聚集写操作创建该文件
	public static void dealStructuralData_Write() throws Exception {
		ByteBuffer bookBuf = ByteBuffer.wrap("java性能优化技巧".getBytes("utf-8"));
		ByteBuffer autBuf = ByteBuffer.wrap("葛一鸣".getBytes("utf-8"));
		int booken = bookBuf.limit();// 记录书名长度
		int authen = autBuf.limit();// 记录作者长度
		ByteBuffer[] bufs = new ByteBuffer[] { bookBuf, autBuf };
		File file = new File("f:/temp/src.txt");
		if (!file.exists()) {
			file.createNewFile();

		}
		FileOutputStream fos = new FileOutputStream(file);
		FileChannel fc = fos.getChannel();
		fc.write(bufs);// 聚集写文件
		fos.close();
	}

	// 处理结构化数据.使用散射读操作，解析文件
	public static void dealStructuralData_Read() throws Exception {

		ByteBuffer bookBuf = ByteBuffer.wrap("java性能优化技巧".getBytes("utf-8"));
		ByteBuffer autBuf = ByteBuffer.wrap("葛一鸣".getBytes("utf-8"));
		int booken = bookBuf.limit();// 记录书名长度
		int authen = autBuf.limit();// 记录作者长度

		// 根据实际信息构造Buffer
		ByteBuffer b1 = ByteBuffer.allocate(booken);// 存放书名的Buffer
		ByteBuffer b2 = ByteBuffer.allocate(authen);// 存放作者的Buffer
		ByteBuffer[] bufs = new ByteBuffer[] { b1, b2 };// Buffer数组
		File file = new File("f:/temp/src.txt");
		FileInputStream fis = new FileInputStream(file);
		FileChannel fc = fis.getChannel();
		fc.read(bufs);// 读入数据
		String bookname = new String(bufs[0].array(), "utf-8");
		String authname = new String(bufs[1].array(), "utf-8");
		System.out.println(bookname + authname);

	}

	// 文件映射到内存
	public static void fileMapToRAM() throws Exception {
		RandomAccessFile raf = new RandomAccessFile("f:/temp/resource.txt", "rw");
		FileChannel fc = raf.getChannel();
		MappedByteBuffer mbb = fc.map(FileChannel.MapMode.READ_WRITE, 0, raf.length());// 将文件映射到内存中
		while (mbb.hasRemaining()) {
			System.out.print((char) mbb.get());
		}
		mbb.put(0, (byte) 111);// 修改文件,将实际数据写到对应的磁盘文件中。
		raf.close();
	}

	// 只读缓冲区。使用只读缓冲区可以保证数据不被修改，同时因为只读缓冲区和原始缓冲区是共享内存块的，因此对原始缓冲的修复，只读缓冲区也是可见的。
	public static void readOnlyBuffer() {
		ByteBuffer b = ByteBuffer.allocate(15);// 分配15个字节的缓冲区
		for (int i = 0; i < 10; i++) {
			b.put((byte) i);// 填充数据
		}
		ByteBuffer readOnly = b.asReadOnlyBuffer();// 创建只读缓冲区
		readOnly.flip();
		while (readOnly.hasRemaining()) {
			System.out.print(readOnly.get() + " ");
		}
		System.out.println();
		b.put(2, (byte) 20);
		// readOnly.put(2, (byte) 20);// 这句话会抛出java.nio.ReadOnlyBufferException异常
		readOnly.flip();
		while (readOnly.hasRemaining()) {
			System.out.print(readOnly.get() + " ");// 新的改动，在只读缓冲区内可见
		}
	}

	// 缓冲区分片,子缓冲区和父缓冲区共享数据
	public static void bufferShard() {
		ByteBuffer b = ByteBuffer.allocate(15);// 分配15个字节的缓冲区
		for (int i = 0; i < 10; i++) {
			b.put((byte) i);// 填充数据
		}
		b.position(2);
		b.limit(6);
		ByteBuffer subBuffer = b.slice();// 生成子缓冲区
		for (int i = 0; i < subBuffer.capacity(); i++) {
			byte bb = subBuffer.get(i);
			bb *= 10;
			subBuffer.put(i, bb);
		}
		b.position(0);
		b.limit(b.capacity());// 重置父缓冲区，并查看父缓冲区数据
		while (b.hasRemaining()) {
			System.out.print(b.get() + " ");
		}
	}

	// 复制缓冲区
	public static void copyBuffer() {
		ByteBuffer b = ByteBuffer.allocate(15);// 分配15个字节的缓冲区
		for (int i = 0; i < 10; i++) {
			b.put((byte) i);// 填充数据
		}
		ByteBuffer c = b.duplicate();// 复制当前缓冲区
		System.out.println("After b.duplicate()");
		System.out.println(b);// java.nio.HeapByteBuffer[pos=10 lim=15 cap=15]
		System.out.println(c);// java.nio.HeapByteBuffer[pos=10 lim=15 cap=15]
		c.flip();// 重置缓冲区
		System.out.println("After c.flip()");
		System.out.println(b);// java.nio.HeapByteBuffer[pos=10 lim=15 cap=15]
		System.out.println(c);// java.nio.HeapByteBuffer[pos=0 lim=10 cap=15]
		c.put((byte) 100);// 向缓冲区c存入数据
		System.out.println("After c.put((byte) 100);");
		System.out.println("b.get(0)=" + b.get(0));// b.get(0)=100
		System.out.println("c.get(0)=" + c.get(0));// c.get(0)=100
	}

	// position、limit、capacity
	public static void bufferConcept() {
		ByteBuffer b = ByteBuffer.allocate(15);// 15个字节大小的缓冲区
		System.out.println("limit=" + b.limit() + " capacity=" + b.capacity() + " position=" + b.position());
		for (int i = 0; i < 10; i++) {// 存入10个字节数据
			b.put((byte) i);
		}
		System.out.println("limit=" + b.limit() + " capacity=" + b.capacity() + " position=" + b.position());
		b.flip();// 重置position
		System.out.println("limit=" + b.limit() + " capacity=" + b.capacity() + " position=" + b.position());
		for (int i = 0; i < 5; i++) {// 存入10个字节数据
			System.out.print(b.get());
		}
		System.out.println();
		System.out.println("limit=" + b.limit() + " capacity=" + b.capacity() + " position=" + b.position());
		b.flip();
		System.out.println("limit=" + b.limit() + " capacity=" + b.capacity() + " position=" + b.position());
	}

	// mark()用于记录当前位置，reset()函数用于恢复到mark所在的位置
	public static void markAndReset() {
		ByteBuffer buffer = ByteBuffer.allocate(15);
		for (int i = 0; i < 10; i++) {// 存入10个字节数据
			buffer.put((byte) i);
		}
		buffer.flip();// 准备读
		for (int i = 0; i < buffer.limit(); i++) {
			System.out.print(buffer.get());
			if (i == 4) {
				buffer.mark();// 在第4个位置做mark
				System.out.print("(mark at " + i + ")");

			}
		}
		buffer.reset();// 回到mark的位置，并处理后续数据
		System.out.println("\nreset to mark");
		while (buffer.hasRemaining()) {// 后续所有数据都将被处理
			System.out.print(buffer.get());

		}
		System.out.println();
	}

	// 文件复制
	public static void nioCopyFile(String resource, String destination) throws IOException {
		FileInputStream fis = new FileInputStream(resource);
		FileOutputStream fos = new FileOutputStream(destination);
		FileChannel readChannel = fis.getChannel();// 读文件通道
		FileChannel writeChannel = fos.getChannel();// 写文件通道
		// Buffer的创建方式一：从堆中分配
		ByteBuffer buffer = ByteBuffer.allocate(1024);// 读入数据缓存
		// Buffer的创建方式二：从既有的数组中创建
		byte[] array = new byte[1024];
		ByteBuffer buffer2 = ByteBuffer.wrap(array);
		while (true) {
			buffer.clear();
			int len = readChannel.read(buffer);// 读入数据
			if (len == -1) {// 读取完毕
				break;

			}
			buffer.flip();
			writeChannel.write(buffer);// 写入文件
		}
		readChannel.close();
		writeChannel.close();
	}

}
