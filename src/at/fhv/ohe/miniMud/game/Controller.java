package at.fhv.ohe.miniMud.game;


import at.fhv.ohe.miniMud.map.Directions;
import at.fhv.ohe.miniMud.map.FieldFunctions.ActionFunctions;
import at.fhv.ohe.miniMud.map.MapController;
import at.fhv.ohe.miniMud.map.Player;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Oliver H on 15.04.2017.
 */
public class Controller extends Thread {
    ConnectionHandler _conHand;
    private boolean _isActive;
    private List<String> _exQueue;
    private Player _player;


    public Controller(ConnectionHandler connectionHandler) {
        _exQueue = new LinkedList<>();
        _isActive = true;
        _conHand = connectionHandler;
        this.start();
    }

    @Override
    public void run() {
        _conHand.sendToClient("Hello and Welcome");
        _conHand.sendToClient("\r\nChose a Map\n");
        for (String item : GameHub.getInstance().getMapControllerNames()) {
            _conHand.sendToClient("\r\n" + item);
        }
        _conHand.sendToClient("\n\r\nMUD>");

        MapController mc = null;
        while (_player == null) {
            try {
                if (isNewCommandAvariable()) {
                    String[] get = getNextQueueItem().split(" ");
                    if (mc == null) {
                        try {
                            mc = GameHub.getInstance().getMapController(Integer.parseInt(get[0]));
                            if (mc != null) {
                                _conHand.sendToClient("\r\nAnd Now your Name");
                                _conHand.sendToClient("\r\nMUD>");
                            }
                        } catch (NumberFormatException e) {
                            _conHand.sendToClient("\r\nI didnt get it..");
                            _conHand.sendToClient("\r\nMUD>");
                        }
                    } else {
                        try {
                            _player = mc.addPlayer(get[0]);
                        } catch (IllegalArgumentException e) {
                            _conHand.sendToClient("\r\nTry an another one :)");
                            _conHand.sendToClient("\r\nMUD>");
                        }
                    }
                } else {
                    synchronized (this) {
                        this.wait();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        _player.setOutStream(_conHand);
        _player.playerOutputStream("Welcome now you can Play :)");
        _player.playerOutputStream("MUD>");
        while (_isActive) {
            synchronized (this) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            String[] get = getNextQueueItem().split(" ");
            get[0].toLowerCase();
            switch (get[0]) {
                case "lookhere":
                    _player.lookHere();
                    break;
                case "lookaround":
                    _player.lookAround();
                    break;

                case "walk":
                    try {
                        _player.move(Directions.valueOf(get[1].toUpperCase()));
                    } catch (IllegalArgumentException e) {
                        _player.playerOutputStream("I dont understand??");
                    }
                    break;

                case "getInv":
                    _player.playerOutputStream("<My Inventory>");
                    _player.lookInventorry();
                    break;

                case "action":
                    try {
                        _player.action(ActionFunctions.valueOf(get[1]));
                    } catch (IllegalArgumentException e) {
                        _player.playerOutputStream("I dont understand??");
                    }
                    break;

                case "exit":
                    _player.logOutFromGame();
                    _isActive = false;
                    _conHand.logOutPerformed();
                    break;

                case "help":
                    break;

                default:
                    _player.playerOutputStream("I dont understand??");

            }
            _player.playerOutputStream("MUD>");
        }
    }

    private boolean isNewCommandAvariable() {
        return (_exQueue.isEmpty()) ? false : true;
    }

    private String getNextQueueItem() {
        if (_exQueue.isEmpty()) {
            return null;
        }
        String s = _exQueue.get(0);
        _exQueue.remove(0);
        return s;
    }

    public void toExecuteQueue(String s) {
        _exQueue.add(s);
    }

    public synchronized void playerLost() {

    }
}
