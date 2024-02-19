package org.dash.sdk;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

public class BaseTest {
    static {
        System.loadLibrary("sdklib");
    }

    static byte [] publicKey = new byte[] { 0x02, 0x9, 0xA, 0xB, 0, 0, 0, 0, 0, 0, 0, 0, 0,0, 0, 0, 0,0, 0, 0, 0,0, 0, 0, 0,0, 0, 0, 0,0, 0, 0, 0 };

    static byte [] identifier = new byte[] { 0xA, 0xB, 0xC, 0, 0, 0, 0, 0, 0, 0, 0, 0,0, 0, 0, 0,0, 0, 0, 0,0, 0, 0, 0,0, 0, 0, 0,0, 0, 0, 0 };
    static byte [] contractIdentifier = new byte[] { 0xD, 0xE, 0xE, 0, 0, 0, 0, 0, 0, 0, 0, 0,0, 0, 0, 0,0, 0, 0, 0,0, 0, 0, 0,0, 0, 0, 0,0, 0, 0, 0 };

    static MemoryFactory memoryFactory = MemoryFactory.getInstance();

    @BeforeAll
    public static void start() {

    }

    @AfterAll
    public static void end() {
        System.out.printf("objects remaining: %d\n", memoryFactory.size());
    }


}
