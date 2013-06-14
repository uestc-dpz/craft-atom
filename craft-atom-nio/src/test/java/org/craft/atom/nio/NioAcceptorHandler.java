package org.craft.atom.nio;

import org.craft.atom.io.AbstractIoHandler;
import org.craft.atom.io.Channel;

/**
 * @author mindwind
 * @version 1.0, 2011-12-19
 */
public class NioAcceptorHandler extends AbstractIoHandler {
	
	private static final byte LF = 10; 
	private StringBuilder buf = new StringBuilder();

	@Override
	public void channelRead(Channel<byte[]> channel, byte[] bytes) {
		System.out.println("[Nio Acceptor Handler] channel read bytes size=" + bytes.length);
		
		for (byte b : bytes) {
			buf.append((char) b);
		}
		
		if (bytes[bytes.length - 1] == LF) {
			byte[] echoBytes = buf.toString().getBytes();
			System.out.println("\nEcho bytes size=" + echoBytes.length + "\n");
			channel.write(echoBytes);
			buf = new StringBuilder();
		}
	}
	
}