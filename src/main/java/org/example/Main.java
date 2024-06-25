package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws IOException, TelegramApiException {

        new MyTelegramBot("Ellionasabot", "7369611649:AAGw6DmhpzOhcNyl9rP1IeQv-zl5Usgaubk");
    }
}