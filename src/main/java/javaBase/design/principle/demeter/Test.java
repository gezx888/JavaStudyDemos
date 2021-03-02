package javaBase.design.principle.demeter;

/**
 * @className: Test
 * @description: TODO 类描述
 * @author: gezx
 * @date: 2021/3/2
 **/
public class Test {
    public static void main(String[] args) {
        Boss boss = new Boss();
        TeamLeader teamLeader = new TeamLeader();
        boss.commandCheckNumber(teamLeader);
    }
}
