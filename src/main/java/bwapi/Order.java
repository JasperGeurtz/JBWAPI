package bwapi;

import java.util.Arrays;

public enum Order {
    Die(0),
    Stop(1),
    Guard(2),
    PlayerGuard(3),
    TurretGuard(4),
    BunkerGuard(5),
    Move(6),
    ReaverStop(7),
    Attack1(8),
    Attack2(9),
    AttackUnit(10),
    AttackFixedRange(11),
    AttackTile(12),
    Hover(13),
    AttackMove(14),
    InfestedCommandCenter(15),
    UnusedNothing(16),
    UnusedPowerup(17),
    TowerGuard(18),
    TowerAttack(19),
    VultureMine(20),
    StayInRange(21),
    TurretAttack(22),
    Nothing(23),
    Unused_24(24),
    DroneStartBuild(25),
    DroneBuild(26),
    CastInfestation(27),
    MoveToInfest(28),
    InfestingCommandCenter(29),
    PlaceBuilding(30),
    PlaceProtossBuilding(31),
    CreateProtossBuilding(32),
    ConstructingBuilding(33),
    Repair(34),
    MoveToRepair(35),
    PlaceAddon(36),
    BuildAddon(37),
    Train(38),
    RallyPointUnit(39),
    RallyPointTile(40),
    ZergBirth(41),
    ZergUnitMorph(42),
    ZergBuildingMorph(43),
    IncompleteBuilding(44),
    IncompleteMorphing(45),
    BuildNydusExit(46),
    EnterNydusCanal(47),
    IncompleteWarping(48),
    Follow(49),
    Carrier(50),
    ReaverCarrierMove(51),
    CarrierStop(52),
    CarrierAttack(53),
    CarrierMoveToAttack(54),
    CarrierIgnore2(55),
    CarrierFight(56),
    CarrierHoldPosition(57),
    Reaver(58),
    ReaverAttack(59),
    ReaverMoveToAttack(60),
    ReaverFight(61),
    ReaverHoldPosition(62),
    TrainFighter(63),
    InterceptorAttack(64),
    ScarabAttack(65),
    RechargeShieldsUnit(66),
    RechargeShieldsBattery(67),
    ShieldBattery(68),
    InterceptorReturn(69),
    DroneLand(70),
    BuildingLand(71),
    BuildingLiftOff(72),
    DroneLiftOff(73),
    LiftingOff(74),
    ResearchTech(75),
    Upgrade(76),
    Larva(77),
    SpawningLarva(78),
    Harvest1(79),
    Harvest2(80),
    MoveToGas(81),
    WaitForGas(82),
    HarvestGas(83),
    ReturnGas(84),
    MoveToMinerals(85),
    WaitForMinerals(86),
    MiningMinerals(87),
    Harvest3(88),
    Harvest4(89),
    ReturnMinerals(90),
    Interrupted(91),
    EnterTransport(92),
    PickupIdle(93),
    PickupTransport(94),
    PickupBunker(95),
    Pickup4(96),
    PowerupIdle(97),
    Sieging(98),
    Unsieging(99),
    WatchTarget(100),
    InitCreepGrowth(101),
    SpreadCreep(102),
    StoppingCreepGrowth(103),
    GuardianAspect(104),
    ArchonWarp(105),
    CompletingArchonSummon(106),
    HoldPosition(107),
    QueenHoldPosition(108),
    Cloak(109),
    Decloak(110),
    Unload(111),
    MoveUnload(112),
    FireYamatoGun(113),
    MoveToFireYamatoGun(114),
    CastLockdown(115),
    Burrowing(116),
    Burrowed(117),
    Unburrowing(118),
    CastDarkSwarm(119),
    CastParasite(120),
    CastSpawnBroodlings(121),
    CastEMPShockwave(122),
    NukeWait(123),
    NukeTrain(124),
    NukeLaunch(125),
    NukePaint(126),
    NukeUnit(127),
    CastNuclearStrike(128),
    NukeTrack(129),
    InitializeArbiter(130),
    CloakNearbyUnits(131),
    PlaceMine(132),
    RightClickAction(133),
    SuicideUnit(134),
    SuicideLocation(135),
    SuicideHoldPosition(136),
    CastRecall(137),
    Teleport(138),
    CastScannerSweep(139),
    Scanner(140),
    CastDefensiveMatrix(141),
    CastPsionicStorm(142),
    CastIrradiate(143),
    CastPlague(144),
    CastConsume(145),
    CastEnsnare(146),
    CastStasisField(147),
    CastHallucination(148),
    Hallucination2(149),
    ResetCollision(150),
    ResetHarvestCollision(151),
    Patrol(152),
    CTFCOPInit(153),
    CTFCOPStarted(154),
    CTFCOP2(155),
    ComputerAI(156),
    AtkMoveEP(157),
    HarassMove(158),
    AIPatrol(159),
    GuardPost(160),
    RescuePassive(161),
    Neutral(162),
    ComputerReturn(163),
    InitializePsiProvider(164),
    SelfDestructing(165),
    Critter(166),
    HiddenGun(167),
    OpenDoor(168),
    CloseDoor(169),
    HideTrap(170),
    RevealTrap(171),
    EnableDoodad(172),
    DisableDoodad(173),
    WarpIn(174),
    Medic(175),
    MedicHeal(176),
    HealMove(177),
    MedicHoldPosition(178),
    MedicHealToIdle(179),
    CastRestoration(180),
    CastDisruptionWeb(181),
    CastMindControl(182),
    DarkArchonMeld(183),
    CastFeedback(184),
    CastOpticalFlare(185),
    CastMaelstrom(186),
    JunkYardDog(187),
    Fatal(188),
    None(189),
    Unknown(190);

    static final Order[] orders = new Order[190 + 1];

    static {
        Arrays.stream(Order.values()).forEach(v -> orders[v.id] = v);
    }


    final int id;

    Order(final int id) {
        this.id = id;
    }
}
