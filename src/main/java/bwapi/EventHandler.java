package bwapi;

class EventHandler {
    static void operation(BWEventListener eventListener, Game game, final ClientData.Event event) {
        final Unit u;
        final int frames = game.getFrameCount();
        switch (event.getType()) {
            case MatchStart:
                game.init();
                eventListener.onStart();
                break;
            case MatchEnd:
                eventListener.onEnd(event.getV1() != 0);
                break;
            case MatchFrame:
                game.onFrame(frames);
                eventListener.onFrame();
                break;
            //case 3: //MenuFrame
            case SendText:
                eventListener.onSendText(game.botClientData().gameData().getEventStrings(event.getV1()));
                break;
            case ReceiveText:
                eventListener.onReceiveText(game.getPlayer(event.getV1()), game.botClientData().gameData().getEventStrings(event.getV2()));
                break;
            case PlayerLeft:
                eventListener.onPlayerLeft(game.getPlayer(event.getV1()));
                break;
            case NukeDetect:
                eventListener.onNukeDetect(new Position(event.getV1(), event.getV2()));
                break;
            case SaveGame:
                eventListener.onSaveGame(game.botClientData().gameData().getEventStrings(event.getV1()));
                break;
            case UnitDiscover:
                game.unitCreate(event.getV1());
                u = game.getUnit(event.getV1());
                u.updatePosition(frames);
                eventListener.onUnitDiscover(u);
                break;
            case UnitEvade:
                u = game.getUnit(event.getV1());
                u.updatePosition(frames);
                eventListener.onUnitEvade(u);
                break;
            case UnitShow:
                game.unitShow(event.getV1());
                u = game.getUnit(event.getV1());
                u.updatePosition(frames);
                eventListener.onUnitShow(u);
                break;
            case UnitHide:
                game.unitHide(event.getV1());
                u = game.getUnit(event.getV1());
                eventListener.onUnitHide(u);
                break;
            case UnitCreate:
                game.unitCreate(event.getV1());
                u = game.getUnit(event.getV1());
                u.updatePosition(frames);
                eventListener.onUnitCreate(u);
                break;
            case UnitDestroy:
                game.unitHide(event.getV1());
                u = game.getUnit(event.getV1());
                eventListener.onUnitDestroy(u);
                break;
            case UnitMorph:
                u = game.getUnit(event.getV1());
                u.updatePosition(frames);
                eventListener.onUnitMorph(u);
                break;
            case UnitRenegade:
                u = game.getUnit(event.getV1());
                eventListener.onUnitRenegade(u);
                break;
            case UnitComplete:
                game.unitCreate(event.getV1());
                u = game.getUnit(event.getV1());
                eventListener.onUnitComplete(u);
                break;
        }
    }
}