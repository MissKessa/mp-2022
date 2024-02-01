package uo.mp.filter.encrypt;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class DecryptionInputStream extends FilterInputStream {

	public DecryptionInputStream(InputStream in) {
		super(in);
	}

	@Override
	public int read() throws IOException {
		int theByte = super.read();
		
		if (theByte == -1) return -1; // End of file
		
		theByte = theByte == 0 ? 255 : theByte - 1; // Caesar decipher
		return theByte;
	}
}
