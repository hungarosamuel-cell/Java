package br.com.nexuscloud.model;

// RN03.1 - Exceção UNCHECKED (estende RuntimeException): tratamento não é obrigatório. RA:25057253-2.
public class ServidorOfflineException extends RuntimeException {
    public ServidorOfflineException(String msg) { super(msg); }
}
