package it.fabermatica.plurimix;

public class Event<T> {
    String code;
    T data;

    Event(String code, T data) {
        this.code = code;
        this.data = data;
    }
}
