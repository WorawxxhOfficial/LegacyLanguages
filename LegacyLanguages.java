package com.yourworld.LegacyLanguages;

import com.yourworld.LegacyLanguages.commands.LanguageCommand;
import com.yourworld.LegacyLanguages.managers.LanguageManager;
import com.yourworld.LegacyLanguages.managers.PlayerDataManager;
import org.bukkit.plugin.java.JavaPlugin;

public class LegacyLanguages extends JavaPlugin {
    private LanguageManager languageManager;
    private PlayerDataManager playerDataManager;

    @Override
    public void onEnable() {
        saveDefaultConfig();  // ใช้เพื่อให้ config.yml ถูกบันทึกหากยังไม่มี

        // ส่ง default language ไปที่ LanguageManager
        languageManager = new LanguageManager(this, getDefaultLanguage());
        playerDataManager = new PlayerDataManager(this);

        getCommand("language").setExecutor(new LanguageCommand(languageManager, playerDataManager));

        getLogger().info("&5Now!! LegacyLanguages enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("LegacyLanguages disabled!");
    }

    public LanguageManager getLanguageManager() {
        return languageManager;
    }

    public PlayerDataManager getPlayerDataManager() {
        return playerDataManager;
    }

    // เพิ่มเมธอดนี้เพื่อให้สามารถเรียกดูภาษาพื้นฐานได้
    public String getDefaultLanguage() {
        // ใช้การอ่านค่า "default-language" จาก config.yml หรือใช้ค่าเริ่มต้นถ้าไม่มีการตั้งค่าในไฟล์ config.yml
        return getConfig().getString("default-language", "en");  // กำหนด "en" เป็นค่าเริ่มต้น
    }
}
