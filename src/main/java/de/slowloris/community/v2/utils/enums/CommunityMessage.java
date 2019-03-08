package de.slowloris.community.v2.utils.enums;

import de.slowloris.community.v2.core.Main;

public enum CommunityMessage {

    //GENERAL
    ONLY_PLAYERS(getMessage("ONLY_PLAYERS")),
    NO_PERMISSION(getMessage("NO_PERMISSION")),
    WRONG_SYNTAX(getMessage("WRONG_SYNTAX")),
    PLAYER_OFFLINE(getMessage("PLAYER_OFFLINE")),

    //LISTENERS
    USER_JOIN(getMessage("USER_JOIN")),
    USER_QUIT(getMessage("USER_QUIT")),
    CHAT_FORMAT(getMessageNoPrefix("CHAT_FORMAT")),

    //COMMANDS
    LOCATION_SET(getMessage("LOCATION_SET")),
    EDITMODE_ON(getMessage("EDITMODE_ON")),
    EDITMODE_OFF(getMessage("EDITMODE_OFF")),
    EDITMODE_ON_OTHER(getMessage("EDITMODE_ON_OTHER")),
    EDITMODE_OFF_OTHER(getMessage("EDITMODE_OFF_OTHER")),
    FLY_ON(getMessage("FLY_ON")),
    FLY_OFF(getMessage("FLY_OFF")),
    FLY_ON_OTHER(getMessage("FLY_ON_OTHER")),
    FLY_OFF_OTHER(getMessage("FLY_OFF_OTHER")),
    VANISH_ON(getMessage("VANISH_ON")),
    VANISH_OFF(getMessage("VANISH_OFF")),
    VANISH_ON_OTHER(getMessage("VANISH_ON_OTHER")),
    VANISH_OFF_OTHER(getMessage("VANISH_OFF_OTHER"));


    private final String s;

    CommunityMessage(final String s) {
        this.s = s;
    }

    @Override
    public String toString() {
        return this.s;
    }

    private static String getMessage(String path){
        return Main.getPrefix() + getMessageNoPrefix(path);
    }

    private static String getMessageNoPrefix(String path){
        if(Main.getMessageConfiguration().isSet("Message." + path)){
            return Main.getMessageConfiguration().getString("Message." + path).replaceAll("&", "§");
        }else {
            return "§c§lMessage not Found! Please have a look at the §aDocs";
        }
    }
}
