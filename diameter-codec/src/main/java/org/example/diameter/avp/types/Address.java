package org.example.diameter.avp.types;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class Address {
    private final short family;
    private final String ip;
}
