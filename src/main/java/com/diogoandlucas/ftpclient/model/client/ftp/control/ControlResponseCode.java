package com.diogoandlucas.ftpclient.model.client.ftp.control;

public enum ControlResponseCode {

    CODE_110("Restart marker reply"),
    CODE_120("Service ready in %d minutes"),
    CODE_125("Data Connection already open, transfer starting"),
    CODE_150("File status okay, about to open data connection"),
    CODE_200("Command ok"),
    CODE_202("Command not implemented, superfluous at this site"),
    CODE_211("System status, or system help reply"),
    CODE_212("Directory status"),
    CODE_213("File status"),
    CODE_214("Help message"),
    CODE_215("%s system type"),
    CODE_220("Service ready for new user"),
    CODE_221("Service closing control connection. Logged out if appropriate"),
    CODE_225("Data connection open; no transfer in progress"),
    CODE_226("Closing data connection. Requested file action successful"),
    CODE_227("Entering Passive Mode"),
    CODE_230("User logged in, proceed"),
    CODE_250("Requested file action okay, completed"),
    CODE_257("\"PATHNAME\" created"),
    CODE_331("User name okay, need password"),
    CODE_332("Need account for login"),
    CODE_350("Requested file action pending further information"),
    CODE_421("Service not available, closing control connection. This may be a reply to any command if the service knows it must shut down"),
    CODE_425("Can't open data connection"),
    CODE_426("Connection closed; transfer aborted"),
    CODE_450("Requested file action not taken. File unavailable"),
    CODE_451("Requested action aborted: local error in processing"),
    CODE_452("Requested action not taken. Insufficient storage space in system"),
    CODE_501("Syntax error in parameters or arguments"),
    CODE_502("Command not implemented"),
    CODE_503("Bad sequence of commands"),
    CODE_504("Command not implemented for that parameter"),
    CODE_530("Not logged in. Your password is being rejected, contact the server administrator"),
    CODE_532("Need account for storing files"),
    CODE_550("Requested action not taken. File unavailable. Contact the server administrator"),
    CODE_552("Requested file action aborted. Exceeded storage allocation. Contact the server administrator"),
    CODE_553("Requested action not taken. File name not allowed. Try changing the file name, or getting rid of spaces in the file name"),
    CODE_10054("Connection Reset by Peer - The connection was forcibly closed by the remote host"),
    CODE_10060("Can't connect to remote server (Generally a time-out error)"),
    CODE_10061("Can't connect to remote server. The connection is actively refused by the server"),
    CODE_10066("Directory not empty. The server will not delete this directory while there are files/folders in it"),
    CODE_10068("Too many users, server is full. Contact the server administrator");

    private final String description;

    ControlResponseCode(String description){
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}
