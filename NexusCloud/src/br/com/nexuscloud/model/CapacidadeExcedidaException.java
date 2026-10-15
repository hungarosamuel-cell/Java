package br.com.nexuscloud.model;

// RN03.2 - Exceção CHECKED (estende Exception): o compilador obriga tratar ou declarar. RA:25057253-2.
public class CapacidadeExcedidaException extends Exception {
    public CapacidadeExcedidaException(String msg) { super(msg); }
}
