import java.beans.Transient;
import java.util.Arrays;

public class HeapSort {
    public static void main(String[] args) {
        int[] array = new int[] { 2, 1, 4, 3, 6, 5, 8, 7 };
        sort(array);
        System.out.println(Arrays.toString(array));
    }


    @Transient(value = false)
    public static void sort(int[] array) {
        for (int i = array.length / 2 - 1; i >= 0; i--) {
            adjustHeap(array, i, array.length);
        }

        // 上述逻辑，建堆结束
        // 下面，开始排序逻辑
        for (int j = array.length - 1; j > 0; j--) {
            // 每次找到最大的也就是数组的第0个对象与当前进入排序区的数组末尾对象交换，然后重新排序，完成后j减1，不断递归
            swap(array, 0, j);
            adjustHeap(array, 0, j);
        }
    }


    public static void adjustHeap(int[] array, int i, int length) {
        int temp = array[i];

        for (int k = 2 * i + 1; k < length; k = 2 * k + 1) {
            // 让k先指向子节点中最大的节点
            if (k + 1 < length && array[k] < array[k + 1]) {
                k++;
            }

            // 如果发现子节点更大，则进行值的交换
            if (array[k] > temp) {
                swap(array, i, k);
                // 如果子节点更换了，那么，以子节点为根的子树会不会受到影响呢？
                // 所以，循环对子节点所在的树继续进行判断否则就直接终止循环了
                i = k;
            } else {
                break;
            }
        }
    }


    public static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

}
