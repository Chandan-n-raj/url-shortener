package com.chandan.urlshortener.common;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import java.net.MalformedURLException;

@ExtendWith(MockitoExtension.class)
public class UrlUtilTest {

    @Test
    public void shouldThrowExceptionWhenMalformedUrlSuppliedWithoutProtocol() throws MalformedURLException {
        Assertions.assertThrows(MalformedURLException.class, () -> {
            UrlUtil.getBaseUrl("malformed url dummy text");
        });
    }

    @Test
    public void shouldThrowExceptionWhenMalformedUrlSuppliedWithIllegalChars() throws MalformedURLException {
        Assertions.assertThrows(MalformedURLException.class, () -> {
            UrlUtil.getBaseUrl("malformed://example.com/foo");
        });
    }

    @Test
    public void shouldReturnBaseUrlWhenValidUrlSuppliedWithoutPort() throws MalformedURLException {
        Assertions.assertEquals(UrlUtil.getBaseUrl("http://example.com/foo"), "http://example.com/");
    }

    @Test
    public void shouldReturnBaseUrlWhenValidUrlSuppliedWithPort() throws MalformedURLException {
        Assertions.assertEquals(UrlUtil.getBaseUrl("http://example.com:8080/foo"), "http://example.com:8080/");
    }
}
