package org.example.diameter.avp;

import lombok.Getter;

public abstract class Avp<T> {
    @Getter
    private AvpHeader header;
    //reference from original buffer
    private byte[] buffer;
    // position in the diameter packet where this avp starts (including header)
    private final int position;
    private T data;

    // constructer when reading bytes
    public Avp(AvpHeader header, byte[] buffer, int position) {
        this.header = header;
        this.buffer = buffer;
        this.position = position;
        // lazy decoding
        //data = this.decode(buffer, position, header);
    }

    public Avp(T data) {
        this.data = data;
        this.position = 0;
    }

    public T getData() {
        if (null == this.data) {
            this.data = this.decode(buffer, position, header);
        }
        return data;
    }

    public abstract T decode(byte[] buffer, int position, AvpHeader header);

}
