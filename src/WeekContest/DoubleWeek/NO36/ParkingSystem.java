package WeekContest.DoubleWeek.NO36;

/**
 * @author zzj
 * @version 1.0
 * @date 2020/10/3 20:51
 * 输入：
 * ["ParkingSystem", "addCar", "addCar", "addCar", "addCar"]
 * [[1, 1, 0], [1], [2], [3], [1]]
 * 输出：
 * [null, true, true, false, false]
 * <p>
 * 解释：
 * ParkingSystem parkingSystem = new ParkingSystem(1, 1, 0);
 * parkingSystem.addCar(1); // 返回 true ，因为有 1 个空的大车位
 * parkingSystem.addCar(2); // 返回 true ，因为有 1 个空的中车位
 * parkingSystem.addCar(3); // 返回 false ，因为没有空的小车位
 * parkingSystem.addCar(1); // 返回 false ，因为没有空的大车位，唯一一个大车位已经被占据了
 */
class ParkingSystem {

    private int[] carNum = new int[3];

    public ParkingSystem(int big, int medium, int small) {
        carNum[0] = big;
        carNum[1] = medium;
        carNum[2] = small;
    }

    public boolean addCar(int carType) {
        if (carType == 1) {
            if (carNum[0] > 0) {
                carNum[0]--;
                return true;
            }
        } else if (carType == 2) {
            if (carNum[1] > 0) {
                carNum[1]--;
                return true;
            }
        } else if (carType == 3) {
            if (carNum[2] > 0) {
                carNum[2]--;
                return true;
            }
        }
        return false;
    }
}
