package com.hit.algorithm;

public interface IEncryptionAlgoFamily <T> {
    public T encrypt(T data);
    public T decrypt(T data);
}
