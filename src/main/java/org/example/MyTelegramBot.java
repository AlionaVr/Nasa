package org.example;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class MyTelegramBot extends TelegramLongPollingBot {
    private final String BOT_NAME;
    private final String BOT_TOKEN;
    private static String URL = "https://api.nasa.gov/planetary/apod" +
            "?api_key=mxsSItaWLL3vxJcO5Fhwz20cmyZZta7grFaTTUT9";

    public MyTelegramBot(String BOT_NAME, String BOT_TOKEN) throws TelegramApiException {
        this.BOT_NAME = BOT_NAME;
        this.BOT_TOKEN = BOT_TOKEN;

        TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
        botsApi.registerBot(this);

    }


    @Override
    public void onUpdateReceived(Update update) {
        // TODO
        if (update.hasMessage() && update.getMessage().hasText()) {
            long chatId = update.getMessage().getChatId();
            String text = update.getMessage().getText();
            String[] splittedText = text.split(" ");
            String action = splittedText[0];
            switch (action) {
                case "/start":
                    sendMessage("Holy! I'm a bot EllioNasa. I can send you image of day from Nasa. Just print me '/image' or '/date 2024-06-12' ", chatId);
                    break;
                case "/help":
                    sendMessage("If you want to get image, inter '/image' or  '/date 2024-06-12'", chatId);
                    break;
                case "/image":
                    String image = Utils.getUrl(URL);
                    sendMessage(image, chatId);
                    break;

                case "/date":
                    String date = splittedText[1];
                    image = Utils.getUrl(URL + "&date=" + date);
                    sendMessage(image, chatId);
                    break;
                default:
                    sendMessage("Sorry, I'm not a wizard :(, I don't know what I should do. For additional information print '/help' ", chatId);

            }
        }
    }

    private void sendMessage(String msg, long chatId) {
        SendMessage message = new SendMessage(); // Create a SendMessage object with mandatory fields
        message.setChatId(chatId);
        message.setText(msg);

        try {
            execute(message); // Call method to send the message
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }


    @Override
    public String getBotUsername() {
        // TODO
        return BOT_NAME;
    }

    @Override
    public String getBotToken() {
        // TODO
        return BOT_TOKEN;
    }

}

