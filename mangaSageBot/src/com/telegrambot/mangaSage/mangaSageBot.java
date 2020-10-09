/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.telegrambot.mangaSage;

import java.util.Random;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.methods.send.SendPhoto;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;
import org.telegram.telegrambots.generics.BotSession;

/**
 *
 * @author Lenovo
 */
public class mangaSageBot  extends TelegramLongPollingBot{
 BotSession session;
TelegramBotsApi telegramBotsApi = new TelegramBotsApi();

static{
   ApiContextInitializer.init();  
}
  
    public void start(){
        //Inicia la sesión
        try{
             this.session=telegramBotsApi.registerBot(this);
             
        }catch(TelegramApiRequestException e){
            e.printStackTrace();
            
        }
    }
    
    
    
    
    public String getBotToken() {
        //Es el token que usa Telegram para autenticar que uno es el dueño del bot.
       return "1316487226:AAFf87v9WKeCOF77cC-C4mhV-Iw3GPfxktk";
    }

    @Override
    public void onUpdateReceived(Update update) {
     /*
        Usando el patrón observador, el API dispara un evento de actualización
        cada vez que el usuario envía un mensaje.Según el tipo, usa un objeto del
        tipo SendMessage o SendPhoto(el api sirve para varios más, pero 
        esos son los implementados aquí).
        
        */
       String messageStr=update.getMessage().getText();
       
       SendMessage message =new SendMessage();
       SendPhoto photo=new SendPhoto();
       String msgType="";
       String manga="";
       String[] goodStuff={"One Piece","Domestic na Kanojo/Domestic Girlfriend",
       "Shingeki no Kyojin/Attack on Titan","Gintama","Death note","The God of High School",
       "Tower of God","Vinland Saga","Goblin Slayer","Yakusoku no neverland/The promised neverland",
       "Kaguya-sama wa Kokurasetai/Kaguya-sama:Love is war","One Punch Man","Overlord",
       "Komi-san wa, Komyushou desu/Komi Can't Communicate","Dr Stone","En'en no Shōbōtai/Fire Force",
       "Mairimashita! Iruma-kun/Welcome to Demon School! Iruma-kun","Runway de Waratte/Smile Down the Runway",
       "Kyokō Suiri/ In/Spectre", "Shokugeki no Sōma/ Food Wars!",
       "Go-Tōbun no Hanayome/ The Quintessential Quintuplets",
       "Kimetsu no Yaiba/Demon Slayer","Omniscient Reader","Kanojo, Okarishimasu/Rent-A-Girlfriend","Made in Abyss",
       "Winter Moon","Beruzebubu-jō no Okinimesu Mama/As Miss Beelzebub Likes",
       "Kono Yūsha ga Ore Tsuē Kuse ni Shinchō Sugiru/Cautious Hero: The Hero Is Overpowered but Overly Cautious",
       "Radiant","Rikei ga Koi ni Ochita no de Shōmei Shite Mita/Science Fell in Love, So I Tried to Prove It",
       "Ōshitsu Kyōshi Haine/ The Royal Tutor","Ore wo Suki Nano wa Omae Dake ka yo/Are You Really the Only One Who Likes Me?",
       "Machikado Mazoku/The Demon Girl Next Door","Sewayaki Kitsune no Senko-san/The Helpful Fox Senko-san",
       "Tate no Yūsha no Nariagari/The Rising of the Shield Hero",
       "Bokutachi wa Benkyō ga Dekinai/ We Never Learn",
       "Kobayashi-san Chi no Meidoragon/Miss Kobayashi's Dragon Maid",
       "Tensei Shitara Suraimu Datta Ken/That Time I Got Reincarnated as a Slime",
       "Black Clover","Boku no Hīrō Akademia/ My Hero Academia"};
       
       String[] sageRating={"S","S","S","S","S","S","S","S","A","A","A","A","A","A","A","A","A","A","A","A"
       ,"A","A","A","A","A","B","B","B","B","B","B","B","B","B","B","B","B","B","B","B"};
       
       Random rand =new Random();
       int random=rand.nextInt(39-0)+0; 
       
       manga=goodStuff[random]+"     Sage Rating:"+sageRating[random];
       
       switch(messageStr){
           case "/start":
               
               message.setText("Hi, fellow weeb, looking for some manga? type /manga if you want me to recommend you some");
               msgType="text";
               break;
           
           case "/manga":
                message.setText(manga);
                msgType="text";
               break;
               
         
       }
       
       
       //Obtiene el id del chat para saber a quien enviar el mensaje
       message.setChatId(update.getMessage().getChatId());
       photo.setChatId(update.getMessage().getChatId());
       
       if(msgType.equalsIgnoreCase("text")){
           
       try{
           
       sendMessage(message);
       
       }catch(TelegramApiException e){
           
           e.printStackTrace();
       }     
       }
      
       if(msgType.equalsIgnoreCase("photo")){
           
         try{
             
       sendPhoto(photo);
       
       }catch(TelegramApiException e){
           
           e.printStackTrace();
           
       }      
       }
      
       
       
       
    }

    @Override
    public String getBotUsername() {
        return "mangaSageBot";
    }

  
    public void onClosing() {
 
     
   
    }
   
}
