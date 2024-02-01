package uo.mp.filter.encrypt;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class EncryptionOutputStream extends FilterOutputStream {

	public EncryptionOutputStream(OutputStream out) {
		super(out);
	}

	@Override
	public void write(int aByte) throws IOException {
		aByte = aByte == 255 ?  0 : aByte + 1; // Caesar cipher
		super.write( aByte );
	}

}

