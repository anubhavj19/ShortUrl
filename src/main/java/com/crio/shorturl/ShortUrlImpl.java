package com.crio.shorturl;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ShortUrlImpl implements ShortUrl{
    HashMap<String, String> urlsMap;
    HashMap<String, Integer> hitCount;

    public ShortUrlImpl(){
        urlsMap = new HashMap<String, String>();
        hitCount = new HashMap<String, Integer>();
    }

    public String registerNewUrl(String longUrl){

        if(urlsMap.containsKey(longUrl)){
            return urlsMap.get(longUrl);
        }

        int count = 9;
        String alphaNumeric = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890abcdefghijklmnopqrstuvwxyz";
        StringBuilder builder = new StringBuilder();

        while(count > 0){
            int characterIndex = (int)(Math.random() * alphaNumeric.length());

            builder.append(alphaNumeric.charAt(characterIndex));

            count--;
        }

        String shortUrl = "http://short.url/" + builder.toString();

        urlsMap.put(longUrl, shortUrl);
        hitCount.put(longUrl, 0);

        return shortUrl;
    }

    public String registerNewUrl(String longUrl, String shortUrl){

        if(urlsMap.containsValue(shortUrl)){
            return null;
        }
        
        urlsMap.put(longUrl, shortUrl);
        hitCount.put(longUrl, 0);

        return shortUrl;
    }

    public String getUrl(String shortUrl){

        if(!urlsMap.containsValue(shortUrl)){
            return null;
        }

        String keyLongUrl = "";

        for(Map.Entry entry: urlsMap.entrySet()){
            if(Objects.equals(shortUrl, entry.getValue())){
                keyLongUrl = entry.getKey().toString();
                break;
            }
        }
        
        int count = hitCount.get(keyLongUrl);
        hitCount.put(keyLongUrl, ++count);

        return keyLongUrl;
    }

    public Integer getHitCount(String longUrl){

        if(!hitCount.containsKey(longUrl)){
            return 0;
        }
        
        return hitCount.get(longUrl);
    }

    public String delete(String longUrl){
        return urlsMap.remove(longUrl);
    }
}