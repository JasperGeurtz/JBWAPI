package bwapi;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.SplittableRandom;
import java.util.stream.Collectors;

@Warmup(iterations = 3, time = 5)
@Measurement(iterations = 3, time = 5)
@Fork(3)
public class ClientDataBenchmark {
    @State(Scope.Thread)
    public static class EmptyState {
        Client client;
        Game game;
        String[] strings;

        @Setup(Level.Invocation)
        public void setup() {
            game = new Game();
            game.botClientData().setBuffer(new WrappedBuffer(ClientData.GameData.SIZE));
            strings = buildStrings();
        }

    }

    @State(Scope.Thread)
    public static class FilledWithStrings {
        Client client;
        ClientData.GameData data;
        Game game;

        @Setup(Level.Invocation)
        public void setup() {
            data = client.liveClientData().gameData();
            game = new Game();
            game.botClientData().setBuffer(new WrappedBuffer(ClientData.GameData.SIZE));
            String[] strings = buildStrings();
            for (String s : strings) {
                GameDataUtils.addString(client.liveClientData().gameData(), s);
            }
        }
    }

    private static String[] buildStrings() {
        SplittableRandom rnd = new SplittableRandom(987654321L);
        String[] strings = new String[GameDataUtils.MAX_COUNT];
        for (int i = 0; i < strings.length; i++) {
            strings[i] = rnd.ints(1022, 0, 9)
                    .mapToObj(Integer::toString)
                    .collect(Collectors.joining());
        }
        return strings;
    }

    @Benchmark
    @Measurement(iterations = 2, time = 2)
    @Warmup(time = 2)
    @Fork(2)
    public void reference(Blackhole blackhole) {
        blackhole.consume(0);
    }

    @Benchmark
    @OperationsPerInvocation(GameDataUtils.MAX_COUNT)
    public int addUnitCommand(EmptyState s) {
        for (int i = 0; i < GameDataUtils.MAX_COUNT; i++) {
            s.game.addUnitCommand(0, 1, 2, 3, 4, 5);
        }
        return s.client.liveClientData().gameData().getCommandCount();
    }

    @Benchmark
    @OperationsPerInvocation(GameDataUtils.MAX_COUNT)
    public int addString(EmptyState s) {
        for (int i = 0; i < GameDataUtils.MAX_COUNT; i++) {
            GameDataUtils.addString(s.client.liveClientData().gameData(), s.strings[i]);
        }
        return s.client.liveClientData().gameData().getStringCount();
    }

    @Benchmark
    @OperationsPerInvocation(GameDataUtils.MAX_COUNT)
    public void getString(FilledWithStrings s, Blackhole blackhole) {
        for (int i = 0; i < GameDataUtils.MAX_COUNT; i++) {
            blackhole.consume(s.data.getStrings(i));
        }
    }
}
