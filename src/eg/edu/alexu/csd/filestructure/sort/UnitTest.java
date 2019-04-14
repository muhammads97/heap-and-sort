package eg.edu.alexu.csd.filestructure.sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
import javax.management.RuntimeErrorException;
import org.junit.Assert;

import eg.edu.alexu.csd.filestructure.sort.IHeap;
import eg.edu.alexu.csd.filestructure.sort.INode;
import eg.edu.alexu.csd.filestructure.sort.ISort;
import eg.edu.alexu.csd.filestructure.sort.TestRunner;

public class UnitTest {

	private final boolean debug = false;

	/**
	 * This test should check the behavior in case of a null root.
	 */
	@org.junit.Test
	public void testRootNull() {

		IHeap<String> heap = (IHeap<String>) TestRunner.getImplementationInstanceForInterface(IHeap.class);
		INode<String> root = null;

		try {
			root = heap.getRoot();
			if (debug)
				System.out.println("TestRootNull: (case null)");
			Assert.assertNull("Root is not null", root);
		} catch (RuntimeErrorException ex) {
			if (debug)
				System.out.println("TestRootNull: (case runtime exception)");
		} catch (Throwable e) {
			TestRunner.fail("Fail to getRoot of heap", e);
		}
	}

	/**
	 * This test should check the behavior of getting root with single element
	 * in heap.
	 */
	@org.junit.Test
	public void testGetRoot() {

		IHeap<String> heap = (IHeap<String>) TestRunner.getImplementationInstanceForInterface(IHeap.class);
		INode<String> root = null;

		try {
			heap.insert("Soso");
			root = heap.getRoot();
			Assert.assertEquals("Soso", root.getValue());
		} catch (Throwable e) {
			TestRunner.fail("Fail to getRoot of heap", e);
		}
	}

	/**
	 * This test should check the behavior of getting root with multiple
	 * elements in heap.
	 */
	@org.junit.Test
	public void testGetRootMulti() {

		IHeap<Integer> heap = (IHeap<Integer>) TestRunner.getImplementationInstanceForInterface(IHeap.class);
		INode<Integer> root = null;

		try {
			Integer max = Integer.MIN_VALUE;
			for (int i = 0; i < 10000; i++) {
				Random r = new Random();
				int val = r.nextInt(Integer.MAX_VALUE);
				heap.insert(val);
				max = Math.max(max, val);
			}
			root = heap.getRoot();
			Assert.assertEquals(max, root.getValue());
		} catch (Throwable e) {
			TestRunner.fail("Fail to getRoot of heap", e);
		}
	}

	/**
	 * This test should check the behavior of getting root after adding elements
	 * to heap then remove all of them.
	 */
	@org.junit.Test
	public void testGetRootInsertingThenRemoving() {

		IHeap<Integer> heap = (IHeap<Integer>) TestRunner.getImplementationInstanceForInterface(IHeap.class);
		INode<Integer> root = null;

		try {
			for (int i = 0; i < 10000; i++) {
				heap.insert(3);
			}
			for (int i = 0; i < 10000; i++) {
				heap.extract();
			}
			root = heap.getRoot();
			if (debug)
				System.out.println("TestRootNull: (case null)");
			Assert.assertNull("Root is not null", root);
		} catch (RuntimeErrorException ex) {
			if (debug)
				System.out.println("TestRootNull: (case runtime exception)");
		} catch (Throwable e) {
			TestRunner.fail("Fail to getRoot of heap", e);
		}

	}

	/**
	 * This test should get the inital size of the heap.
	 */
	@org.junit.Test
	public void testHeapSize() {

		IHeap<Integer> heap = (IHeap<Integer>) TestRunner.getImplementationInstanceForInterface(IHeap.class);

		try {
			Assert.assertEquals(0, heap.size());
		} catch (Throwable e) {
			TestRunner.fail("Fail to get Heap size", e);
		}
	}
	
	/**
	 * This test should check the size while inserting and extracting elements
	 * from heap.
	 */
	@org.junit.Test
	public void testHeapSizeWithInsertionAndExtraction() {

		IHeap<Integer> heap = (IHeap<Integer>) TestRunner.getImplementationInstanceForInterface(IHeap.class);

		try {
			Random r2 = new Random();
			int check = r2.nextInt(10000);

			for (int i = 0; i < 10000; i++) {
				Random r = new Random();
				int val = r.nextInt(Integer.MAX_VALUE);
				heap.insert(val);
				check--;

				if (check == 0)
					Assert.assertEquals(i + 1, heap.size());
			}

			check = r2.nextInt(1000);
			for (int i = 0; i < 10000; i++) {
				heap.extract();
				check--;

				if (check == 0)
					Assert.assertEquals(10000 - i - 1, heap.size());
			}

		} catch (Throwable e) {
			TestRunner.fail("Fail to get heap size", e);
		}
	}

	/**
	 * This test should check getting children and parents pointers.
	 */
	@org.junit.Test
	public void testGetChildrenAndParentPointers() {

		IHeap<Integer> heap = (IHeap<Integer>) TestRunner.getImplementationInstanceForInterface(IHeap.class);

		try {
			heap.insert(1);
			heap.insert(2);
			heap.insert(4);
			heap.insert(3);
			heap.insert(0);
			heap.insert(5);

			INode<Integer> root = heap.getRoot();
			Assert.assertEquals(5, root.getValue().intValue());
			Assert.assertEquals(3, root.getLeftChild().getValue().intValue());
			Assert.assertEquals(4, root.getRightChild().getValue().intValue());
			Assert.assertEquals(1, root.getLeftChild().getLeftChild().getValue().intValue());
			Assert.assertEquals(0, root.getLeftChild().getRightChild().getValue().intValue());
			Assert.assertEquals(2, root.getRightChild().getLeftChild().getValue().intValue());
			Assert.assertEquals(5, root.getLeftChild().getLeftChild().getParent().getParent().getValue().intValue());
		} catch (Throwable e) {
			TestRunner.fail("Fail to get child/parent pointer", e);
		}
	}

	/**
	 * This test should check getting null children and parents pointers.
	 */
	@org.junit.Test
	public void testGetNullChildrenAndParentPointers() {

		IHeap<Integer> heap = (IHeap<Integer>) TestRunner.getImplementationInstanceForInterface(IHeap.class);

		try {
			heap.insert(1);
			Assert.assertNull(heap.getRoot().getLeftChild());
			Assert.assertNull(heap.getRoot().getRightChild());
			Assert.assertNull(heap.getRoot().getParent());
		} catch (Throwable e) {
			TestRunner.fail("Fail to get child/parent pointer", e);
		}
	}

	/**
	 * This test should check sending null to heapify.
	 */
	@org.junit.Test
	public void testHeapifyWithNullParameter() {
		IHeap<String> heap = (IHeap<String>) TestRunner.getImplementationInstanceForInterface(IHeap.class);

		try {
			heap.insert("soso");
			heap.heapify(null);
			if (debug)
				System.out.println("TestHeapifyWithNullParameter (case ignoring element)");
			Assert.assertEquals(1, heap.size());
		} catch (RuntimeErrorException ex) {
			if (debug)
				System.out.println("TestHeapifyWithNullParameter (case runtime exception)");
		} catch (Throwable e) {
			TestRunner.fail("Fail to handle null in heapify method", e);
		}
	}

	/**
	 * This test should check insert with null parameter.
	 */
	@org.junit.Test
	public void testInsertWithNullParameter() {
		IHeap<String> heap = (IHeap<String>) TestRunner.getImplementationInstanceForInterface(IHeap.class);

		try {
			heap.insert(null);
			if (debug)
				System.out.println("TestInsertWithNullParameter (case ignoring element)");
			Assert.assertEquals(0, heap.size());
		} catch (RuntimeErrorException ex) {
			if (debug)
				System.out.println("TestInsertWithNullParameter (case runtime exception)");
		} catch (Throwable e) {
			TestRunner.fail("Fail to handle null in insert method", e);
		}
	}

	/**
	 * This test should check normal insertion.
	 */
	@org.junit.Test
	public void testInsertNormal() {
		IHeap<String> heap = (IHeap<String>) TestRunner.getImplementationInstanceForInterface(IHeap.class);

		try {
			heap.insert("Soso");
			heap.insert("Toto");
			Assert.assertEquals(2, heap.size());
			Assert.assertEquals("Toto", heap.getRoot().getValue());
		} catch (Throwable e) {
			TestRunner.fail("Fail to insert to heap", e);
		}
	}

	/**
	 * This test should check insert is O(lg n).
	 */
	@org.junit.Test(timeout = 7000)
	public void testInsertIsLgN() {
		IHeap<String> heap = (IHeap<String>) TestRunner.getImplementationInstanceForInterface(IHeap.class);

		try {
			for (int i = 0; i < 10000000; i++)
				heap.insert("soso");
		} catch (Throwable e) {
			TestRunner.fail("Fail to insert to heap", e);
		}
	}

	/**
	 * This test should check normal extraction.
	 */
	@org.junit.Test
	public void testExtractNormal() {
		IHeap<Integer> heap = (IHeap<Integer>) TestRunner.getImplementationInstanceForInterface(IHeap.class);

		try {
			Integer max = Integer.MIN_VALUE;
			Integer secondMax = Integer.MIN_VALUE;

			for (int i = 0; i < 10000; i++) {
				Random r = new Random();
				int val = r.nextInt(Integer.MAX_VALUE);
				heap.insert(val);
				if (val > max) {
					secondMax = max;
					max = val;
				} else if (val >= secondMax) {
					secondMax = val;
				}
			}

			Assert.assertEquals(max, heap.extract());
			Assert.assertEquals(secondMax, heap.extract());
		} catch (Throwable e) {
			TestRunner.fail("Fail to extract element from heap", e);
		}
	}

	/**
	 * This test should check extract from empty heap.
	 */
	@org.junit.Test
	public void testExtractEmptyHeap() {
		IHeap<Integer> heap = (IHeap<Integer>) TestRunner.getImplementationInstanceForInterface(IHeap.class);

		try {
			Integer i = heap.extract();
			if (debug)
				System.out.println("testExtractEmptyHeap (case null element)");
			Assert.assertNull(i);
		} catch (RuntimeErrorException ex) {
			if (debug)
				System.out.println("testExtractEmptyHeap (case runtime exception)");
		} catch (Throwable e) {
			TestRunner.fail("Fail to handle extracting from empty heap", e);
		}
	}

	/**
	 * This test should check extract is running in O(lg n).
	 */
	@org.junit.Test(timeout = 7000)
	public void testExtractLgN() {
		IHeap<String> heap = (IHeap<String>) TestRunner.getImplementationInstanceForInterface(IHeap.class);

		try {
			for (int i = 0; i < 10000000; i++)
				heap.insert("soso");
			for (int i = 0; i < 10000000; i++)
				heap.extract();
		} catch (Throwable e) {
			TestRunner.fail("Fail to handle inserting or extracting from empty heap", e);
		}
	}

	/**
	 * This test should check extract after inserting elements in heap and then
	 * remove them.
	 */
	@org.junit.Test
	public void testExtractAfterInsertingAndExtractingAllElements() {
		IHeap<String> heap = (IHeap<String>) TestRunner.getImplementationInstanceForInterface(IHeap.class);

		try {
			for (int i = 0; i < 1000; i++)
				heap.insert("soso");
			for (int i = 0; i < 1000; i++)
				heap.extract();

			String s = heap.extract();
			if (debug)
				System.out.println("testExtractAfterInsertingAndExtractingAllElements (case null element)");
			Assert.assertNull(s);
		} catch (RuntimeErrorException ex) {
			if (debug)
				System.out.println("testExtractAfterInsertingAndExtractingAllElements (case runtime exception)");
		} catch (Throwable e) {
			TestRunner.fail("Fail to handle extracting after inserting and removing all elements", e);
		}
	}

	/**
	 * This test should check build heap with null
	 */
	@org.junit.Test
	public void testBuildHeapWithNull() {
		IHeap<String> heap = (IHeap<String>) TestRunner.getImplementationInstanceForInterface(IHeap.class);

		try {
			heap.build(null);
			if (debug)
				System.out.println("testBuildHeapWithNull (case ignore input)");
			Assert.assertEquals(0, heap.size());
		} catch (RuntimeErrorException ex) {
			if (debug)
				System.out.println("testBuildHeapWithNull (case runtime exception)");
		} catch (Throwable e) {
			TestRunner.fail("Fail to handle build with null paramter", e);
		}
	}

	/**
	 * This test should check build heap with empty array
	 */
	@org.junit.Test
	public void testBuildHeapWithEmptyArray() {
		IHeap<String> heap = (IHeap<String>) TestRunner.getImplementationInstanceForInterface(IHeap.class);

		try {
			heap.build(new ArrayList<>());
			Assert.assertEquals(0, heap.size());
		} catch (Throwable e) {
			TestRunner.fail("Fail to handle build with empty array", e);
		}
	}	
	
	/**
	 * This test should check build runs in O(n).
	 */
	@org.junit.Test(timeout = 1000)
	public void testBuildIsN() {
		IHeap<Integer> heap = (IHeap<Integer>) TestRunner.getImplementationInstanceForInterface(IHeap.class);

		try {
			ArrayList<Integer> arr = new ArrayList<>();
			for (int i = 0; i < 1000000; i++)
				arr.add(i);

			heap.build(arr);
		} catch (Throwable e) {
			TestRunner.fail("Fail to build heap", e);
		}
	}

	/**
	 * This test should check normal build heap.
	 */
	@org.junit.Test
	public void testNormalBuild() {
		IHeap<Integer> heap = (IHeap<Integer>) TestRunner.getImplementationInstanceForInterface(IHeap.class);

		try {
			ArrayList<Integer> arr = new ArrayList<>();
			for (int i = 0; i < 1000000; i++)
				arr.add(i);

			heap.build(arr);
			for (int i = 1000000 - 1; i >= 0; i--)
				Assert.assertEquals(i, heap.extract().intValue());

		} catch (Throwable e) {
			TestRunner.fail("Fail to build heap", e);
		}
	}

	/**
	 * This test should check random build heap.
	 */
	@org.junit.Test
	public void testRandomBuild() {
		IHeap<Integer> heap = (IHeap<Integer>) TestRunner.getImplementationInstanceForInterface(IHeap.class);

		try {
			ArrayList<Integer> arr = new ArrayList<>();
			Random r = new Random();
			PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

			for (int i = 0; i < 1000000; i++) {
				int val = r.nextInt(Integer.MAX_VALUE);
				arr.add(val);
				pq.add(val);
			}

			heap.build(arr);
			for (int i = 0; i < 1000000; i++)
				Assert.assertEquals(pq.poll().intValue(), heap.extract().intValue());

		} catch (Throwable e) {
			TestRunner.fail("Fail to build heap", e);
		}
	}

	/**
	 * This test should check heap sort with null paramter.
	 */
	@org.junit.Test
	public void testHeapSortWithNullParameter() {
		ISort<Integer> sort = (ISort<Integer>) TestRunner.getImplementationInstanceForInterface(ISort.class);

		try {
			IHeap<Integer> heap = sort.heapSort(null);
			if (debug)
				System.out.println("testHeapSortWithNullParameter (case ignore input)");
			Assert.assertEquals(0, heap.size());
		} catch (RuntimeErrorException ex) {
			if (debug)
				System.out.println("testHeapSortWithNullParameter (case runtime exception)");
		} catch (Throwable e) {
			TestRunner.fail("Fail in heap sort", e);
		}
	}

	/**
	 * This test should check heap sort with empty array.
	 */
	@org.junit.Test
	public void testHeapSortWithEmptyArray() {
		ISort<Integer> sort = (ISort<Integer>) TestRunner.getImplementationInstanceForInterface(ISort.class);

		try {
			IHeap<Integer> heap = sort.heapSort(new ArrayList<>());
			if (debug)
				System.out.println("testHeapSortWithNullParameter (case ignore input)");
			Assert.assertEquals(0, heap.size());
		} catch (Throwable e) {
			TestRunner.fail("Fail in heap sort", e);
		}
	}

	/**
	 * This test should check normal heap sort with small input.
	 */
	@org.junit.Test
	public void testNormalHeapSortSmallInput() {
		ISort<Integer> sort = (ISort<Integer>) TestRunner.getImplementationInstanceForInterface(ISort.class);

		try {
			ArrayList<Integer> arr = new ArrayList<>();
			Random r = new Random();

			for (int i = 0; i < 10; i++) {
				int val = r.nextInt(Integer.MAX_VALUE);
				arr.add(val);
			}

			IHeap<Integer> heap = sort.heapSort(arr);
			Collections.sort(arr);

			ArrayList<Integer> heapRes = new ArrayList<>();
			Queue<INode<Integer>> q = new LinkedList<>();

			q.add(heap.getRoot());

			while (!q.isEmpty()) {
				INode<Integer> curNode = q.poll();

				if (curNode == null)
					continue;
				heapRes.add(curNode.getValue());
				q.add(curNode.getLeftChild());
				q.add(curNode.getRightChild());
			}

			Assert.assertEquals(arr.size(), heapRes.size());

			for (int i = 0; i < arr.size(); i++)
				Assert.assertEquals(arr.get(i), heapRes.get(i));

		} catch (Throwable e) {
			TestRunner.fail("Fail in heap sort", e);
		}
	}

	/**
	 * This test should check normal heap sort.
	 */
	@org.junit.Test(timeout = 5000)
	public void testNormalHeapSortBigInput() {
		ISort<Integer> sort = (ISort<Integer>) TestRunner.getImplementationInstanceForInterface(ISort.class);

		try {
			ArrayList<Integer> arr = new ArrayList<>();
			Random r = new Random();

			for (int i = 0; i < 1000000; i++) {
				int val = r.nextInt(Integer.MAX_VALUE);
				arr.add(val);
			}

			IHeap<Integer> heap = sort.heapSort(arr);
			Collections.sort(arr);

			ArrayList<Integer> heapRes = new ArrayList<>();
			Queue<INode<Integer>> q = new LinkedList<>();

			q.add(heap.getRoot());

			while (!q.isEmpty()) {
				INode<Integer> curNode = q.poll();

				if (curNode == null)
					continue;
				heapRes.add(curNode.getValue());
				q.add(curNode.getLeftChild());
				q.add(curNode.getRightChild());
			}

			Assert.assertEquals(arr.size(), heapRes.size());

			for (int i = 0; i < arr.size(); i++)
				Assert.assertEquals(arr.get(i), heapRes.get(i));
		} catch (Throwable e) {
			TestRunner.fail("Fail in heap sort", e);
		}
	}

	/**
	 * This test should check slow sort with null paramter.
	 */
	@org.junit.Test
	public void testSlowSortWithNullParameter() {
		ISort<Integer> sort = (ISort<Integer>) TestRunner.getImplementationInstanceForInterface(ISort.class);

		try {
			sort.sortSlow(null);
			if (debug)
				System.out.println("testHeapSortWithNullParameter (case ignore input)");
		} catch (RuntimeErrorException ex) {
			if (debug)
				System.out.println("testHeapSortWithNullParameter (case runtime exception)");
		} catch (Throwable e) {
			TestRunner.fail("Fail to slow sort", e);
		}
	}

	/**
	 * This test should check slow sort with empty array.
	 */
	@org.junit.Test
	public void testSlowSortWithEmptyArray() {
		ISort<Integer> sort = (ISort<Integer>) TestRunner.getImplementationInstanceForInterface(ISort.class);

		try {
			sort.sortSlow(new ArrayList<>());
			if (debug)
				System.out.println("testHeapSortWithNullParameter (case ignore input)");
		} catch (Throwable e) {
			TestRunner.fail("Fail to slow sort", e);
		}
	}

	/**
	 * This test should check normal slow sort with small input.
	 */
	@org.junit.Test
	public void testSlowSortWithSmallInput() {
		ISort<Integer> sort = (ISort<Integer>) TestRunner.getImplementationInstanceForInterface(ISort.class);

		try {
			ArrayList<Integer> arr = new ArrayList<>();
			Random r = new Random();

			for (int i = 0; i < 10; i++) {
				int val = r.nextInt(Integer.MAX_VALUE);
				arr.add(val);
			}

			ArrayList<Integer> arr2 = new ArrayList<>(arr);
			sort.sortSlow(arr2);
			Collections.sort(arr);

			Assert.assertEquals(arr.size(), arr2.size());

			for (int i = 0; i < arr.size(); i++)
				Assert.assertEquals(arr.get(i), arr2.get(i));
		} catch (Throwable e) {
			TestRunner.fail("Fail in slow sort", e);
		}
	}

	/**
	 * This test should check normal slow sort with big input.
	 */
	@org.junit.Test(timeout = 10000)
	public void testSlowSortWithBigInput() {
		ISort<Integer> sort = (ISort<Integer>) TestRunner.getImplementationInstanceForInterface(ISort.class);

		try {
			ArrayList<Integer> arr = new ArrayList<>();
			Random r = new Random();

			for (int i = 0; i < 10000; i++) {
				int val = r.nextInt(Integer.MAX_VALUE);
				arr.add(val);
			}

			ArrayList<Integer> arr2 = new ArrayList<>(arr);
			sort.sortSlow(arr2);
			Collections.sort(arr);

			Assert.assertEquals(arr.size(), arr2.size());

			for (int i = 0; i < arr.size(); i++)
				Assert.assertEquals(arr.get(i), arr2.get(i));
		} catch (Throwable e) {
			TestRunner.fail("Fail in slow sort", e);
		}
	}

	/**
	 * This test should check fast sort with null paramter.
	 */
	@org.junit.Test
	public void testFastSortWithNullParameter() {
		ISort<Integer> sort = (ISort<Integer>) TestRunner.getImplementationInstanceForInterface(ISort.class);

		try {
			sort.sortFast(null);
			if (debug)
				System.out.println("testHeapSortWithNullParameter (case ignore input)");
		} catch (RuntimeErrorException ex) {
			if (debug)
				System.out.println("testHeapSortWithNullParameter (case runtime exception)");
		} catch (Throwable e) {
			TestRunner.fail("Fail to fast sort", e);
		}
	}

	/**
	 * This test should check fast sort with empty array.
	 */
	@org.junit.Test
	public void testFastSortWithEmptyArray() {
		ISort<Integer> sort = (ISort<Integer>) TestRunner.getImplementationInstanceForInterface(ISort.class);

		try {
			sort.sortFast(new ArrayList<>());
			if (debug)
				System.out.println("testHeapSortWithNullParameter (case ignore input)");
		} catch (Throwable e) {
			TestRunner.fail("Fail to slow sort", e);
		}
	}

	/**
	 * This test should check normal fast sort with small input.
	 */
	@org.junit.Test
	public void testFastSortWithSmallInput() {
		ISort<Integer> sort = (ISort<Integer>) TestRunner.getImplementationInstanceForInterface(ISort.class);

		try {
			ArrayList<Integer> arr = new ArrayList<>();
			Random r = new Random();

			for (int i = 0; i < 10; i++) {
				int val = r.nextInt(Integer.MAX_VALUE);
				arr.add(val);
			}

			ArrayList<Integer> arr2 = new ArrayList<>(arr);
			sort.sortFast(arr2);
			Collections.sort(arr);

			Assert.assertEquals(arr.size(), arr2.size());

			for (int i = 0; i < arr.size(); i++)
				Assert.assertEquals(arr.get(i), arr2.get(i));
		} catch (Throwable e) {
			TestRunner.fail("Fail in fast sort", e);
		}
	}

	/**
	 * This test should check normal fast sort with big input.
	 */
	@org.junit.Test(timeout = 5000)
	public void testFastSortWithBigInput() {
		ISort<Integer> sort = (ISort<Integer>) TestRunner.getImplementationInstanceForInterface(ISort.class);

		try {
			ArrayList<Integer> arr = new ArrayList<>();
			Random r = new Random();

			for (int i = 0; i < 1000000; i++) {
				int val = r.nextInt(Integer.MAX_VALUE);
				arr.add(val);
			}

			ArrayList<Integer> arr2 = new ArrayList<>(arr);
			sort.sortFast(arr2);
			Collections.sort(arr);

			Assert.assertEquals(arr.size(), arr2.size());

			for (int i = 0; i < arr.size(); i++)
				Assert.assertEquals(arr.get(i), arr2.get(i));
		} catch (Throwable e) {
			TestRunner.fail("Fail in fast sort", e);
		}
	}

	/**
	 * This test should check normal fast sort with reverse input.
	 */
	@org.junit.Test(timeout = 5000)
	public void testFastSortWithReverseInput() {
		ISort<Integer> sort = (ISort<Integer>) TestRunner.getImplementationInstanceForInterface(ISort.class);

		try {
			ArrayList<Integer> arr = new ArrayList<>();

			for (int i = 0; i < 1000000; i++) {
				arr.add(1000000 - i);
			}

			ArrayList<Integer> arr2 = new ArrayList<>(arr);
			sort.sortFast(arr2);
			Collections.sort(arr);

			Assert.assertEquals(arr.size(), arr2.size());

			for (int i = 0; i < arr.size(); i++)
				Assert.assertEquals(arr.get(i), arr2.get(i));
		} catch (Throwable e) {
			TestRunner.fail("Fail in fast sort", e);
		}
	}	
	
	/**
	 * This test should stress test the heap implementation.
	 */
	@org.junit.Test
	public void testStressHeap() {
		IHeap<Integer> heap = (IHeap<Integer>) TestRunner.getImplementationInstanceForInterface(IHeap.class);

		try {
			PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
			Random r = new Random();
			Random pick = new Random();
			
			for (int i = 0; i < 1000000; i++) {
				int numToPick = pick.nextInt(Integer.MAX_VALUE);
				int val = r.nextInt(Integer.MAX_VALUE);
				
				if (numToPick % 4 == 0) {
					if (!pq.isEmpty())
						Assert.assertEquals(pq.poll(), heap.extract());
					else 
						Assert.assertEquals(0, heap.size());
				} else {
					pq.add(val);
					heap.insert(val);
				}
			}
		} catch (Throwable e) {
			TestRunner.fail("Fail in heap", e);
		}		
	}

	/**
	 * This test should stress test the heap implementation with custom comparable object.
	 */
	@org.junit.Test
	public void testStressHeapWithCustomComparable() {
		IHeap<Pair> heap = (IHeap<Pair>) TestRunner.getImplementationInstanceForInterface(IHeap.class);
		String [] randomWords = new String[]{"bells" ,"remain" ,"crabby" ,"comfortable" ,"stamp" ,"quickest" ,
				"sulky" ,"worm" ,"vigorous" ,"grandfather" ,"crook" ,"show" ,"second" ,"water" ,"ask" ,"finger" 
				,"scent" ,"encourage" ,"harsh" ,"kaput" ,"spotted" ,"room" ,"harmony" ,"bear" ,"desk" ,"dramatic" 
				,"leg" ,"elite" ,"drop" ,"overjoyed" ,"suspend" ,"selection" ,"tow" ,"pancake" ,"doubt" ,"laugh" 
				,"coast" ,"slow" ,"narrow" ,"language" ,"hand" ,"preach" ,"shaky" ,"flavor" ,"spark" ,"uptight" 
				,"pail" ,"jog" ,"unadvised" ,"fortunate" ,"exultant" ,"clumsy" ,"rot" ,"train" ,"curtain" ,"spurious" 
				,"middle" ,"dare" ,"wheel" ,"snake" ,"jail" ,"crooked" ,"smoggy" ,"elfin" ,"abnormal" ,"skip" ,"skate" 
				,"basket" ,"amount" ,"invention" ,"vegetable" ,"unequaled" ,"part" ,"erratic" ,"branch" ,"car" ,"glib" 
				,"fish" ,"order" ,"deranged" ,"bomb" ,"overrated" ,"orange" ,"enjoy" ,"judicious" ,"finger" ,"cheap" 
				,"meek" ,"gruesome" ,"defective" ,"wicked" ,"bashful" ,"rotten" ,"ground" ,"delicious" ,"cellar" 
				,"chalk" ,"dress" ,"north" ,"serious"};
		try {
			PriorityQueue<Pair> pq = new PriorityQueue<>(Collections.reverseOrder());
			Random r = new Random();
			Random pick = new Random();
			
			for (int i = 0; i < 1000000; i++) {
				int numToPick = pick.nextInt(Integer.MAX_VALUE);
				Pair p = new Pair(r.nextInt(Integer.MAX_VALUE), randomWords[r.nextInt(randomWords.length)]);
				
				if (numToPick % 4 == 0) {
					if (!pq.isEmpty()) {
						Pair p1 = pq.poll();
						Pair p2 = heap.extract();
						
						Assert.assertEquals(p1.key, p2.key);
						Assert.assertEquals(p1.val, p2.val);
					}
					else 
						Assert.assertEquals(0, heap.size());
				} else {
					pq.add(p);
					heap.insert(p);
				}
			}
		} catch (Throwable e) {
			TestRunner.fail("Fail in heap", e);
		}		
	}	

	/**
	 * This test should stress test slow sort implementation with custom comparable object.
	 */
	@org.junit.Test
	public void testStressSlowSortWithCustomComparable() {
		ISort<Pair> sort = (ISort<Pair>) TestRunner.getImplementationInstanceForInterface(ISort.class);
		String [] randomWords = new String[]{"bells" ,"remain" ,"crabby" ,"comfortable" ,"stamp" ,"quickest" ,
				"sulky" ,"worm" ,"vigorous" ,"grandfather" ,"crook" ,"show" ,"second" ,"water" ,"ask" ,"finger" 
				,"scent" ,"encourage" ,"harsh" ,"kaput" ,"spotted" ,"room" ,"harmony" ,"bear" ,"desk" ,"dramatic" 
				,"leg" ,"elite" ,"drop" ,"overjoyed" ,"suspend" ,"selection" ,"tow" ,"pancake" ,"doubt" ,"laugh" 
				,"coast" ,"slow" ,"narrow" ,"language" ,"hand" ,"preach" ,"shaky" ,"flavor" ,"spark" ,"uptight" 
				,"pail" ,"jog" ,"unadvised" ,"fortunate" ,"exultant" ,"clumsy" ,"rot" ,"train" ,"curtain" ,"spurious" 
				,"middle" ,"dare" ,"wheel" ,"snake" ,"jail" ,"crooked" ,"smoggy" ,"elfin" ,"abnormal" ,"skip" ,"skate" 
				,"basket" ,"amount" ,"invention" ,"vegetable" ,"unequaled" ,"part" ,"erratic" ,"branch" ,"car" ,"glib" 
				,"fish" ,"order" ,"deranged" ,"bomb" ,"overrated" ,"orange" ,"enjoy" ,"judicious" ,"finger" ,"cheap" 
				,"meek" ,"gruesome" ,"defective" ,"wicked" ,"bashful" ,"rotten" ,"ground" ,"delicious" ,"cellar" 
				,"chalk" ,"dress" ,"north" ,"serious"};
		try {
			Random r = new Random();
			ArrayList<Pair> arr = new ArrayList<>();
			
			for (int i = 0; i < 10000; i++) {
				Pair p = new Pair(r.nextInt(Integer.MAX_VALUE), randomWords[r.nextInt(randomWords.length)]);
				arr.add(p);
			}
			
			ArrayList<Pair> copy = new ArrayList<>(arr);
			Collections.sort(arr);
			sort.sortSlow(copy);
			
			for (int i = 0; i < arr.size(); i++) {
				Assert.assertEquals(arr.get(i).key, copy.get(i).key);
				Assert.assertEquals(arr.get(i).val, copy.get(i).val);
			}
			
		} catch (Throwable e) {
			TestRunner.fail("Fail in slow sort", e);
		}		
	}		

	/**
	 * This test should stress test fast sort implementation with custom comparable object.
	 */
	@org.junit.Test
	public void testStressFastSortWithCustomComparable() {
		ISort<Pair> sort = (ISort<Pair>) TestRunner.getImplementationInstanceForInterface(ISort.class);
		String [] randomWords = new String[]{"bells" ,"remain" ,"crabby" ,"comfortable" ,"stamp" ,"quickest" ,
				"sulky" ,"worm" ,"vigorous" ,"grandfather" ,"crook" ,"show" ,"second" ,"water" ,"ask" ,"finger" 
				,"scent" ,"encourage" ,"harsh" ,"kaput" ,"spotted" ,"room" ,"harmony" ,"bear" ,"desk" ,"dramatic" 
				,"leg" ,"elite" ,"drop" ,"overjoyed" ,"suspend" ,"selection" ,"tow" ,"pancake" ,"doubt" ,"laugh" 
				,"coast" ,"slow" ,"narrow" ,"language" ,"hand" ,"preach" ,"shaky" ,"flavor" ,"spark" ,"uptight" 
				,"pail" ,"jog" ,"unadvised" ,"fortunate" ,"exultant" ,"clumsy" ,"rot" ,"train" ,"curtain" ,"spurious" 
				,"middle" ,"dare" ,"wheel" ,"snake" ,"jail" ,"crooked" ,"smoggy" ,"elfin" ,"abnormal" ,"skip" ,"skate" 
				,"basket" ,"amount" ,"invention" ,"vegetable" ,"unequaled" ,"part" ,"erratic" ,"branch" ,"car" ,"glib" 
				,"fish" ,"order" ,"deranged" ,"bomb" ,"overrated" ,"orange" ,"enjoy" ,"judicious" ,"finger" ,"cheap" 
				,"meek" ,"gruesome" ,"defective" ,"wicked" ,"bashful" ,"rotten" ,"ground" ,"delicious" ,"cellar" 
				,"chalk" ,"dress" ,"north" ,"serious"};
		try {
			Random r = new Random();
			ArrayList<Pair> arr = new ArrayList<>();
			
			for (int i = 0; i < 10000; i++) {
				Pair p = new Pair(r.nextInt(Integer.MAX_VALUE), randomWords[r.nextInt(randomWords.length)]);
				arr.add(p);
			}
			
			ArrayList<Pair> copy = new ArrayList<>(arr);
			Collections.sort(arr);
			sort.sortFast(copy);
			
			for (int i = 0; i < arr.size(); i++) {
				Assert.assertEquals(arr.get(i).key, copy.get(i).key);
				Assert.assertEquals(arr.get(i).val, copy.get(i).val);
			}
			
		} catch (Throwable e) {
			TestRunner.fail("Fail in fast sort", e);
		}		
	}		

	
	private class Pair implements Comparable<Pair>{
		private int key;
		private String val;
		
		public Pair(int key, String val) {
			this.key = key;
			this.val = val;
		}
		
		@Override
		public int compareTo(Pair o) {
			String s1 = val + Objects.hash(key, val);
			String s2 = o.val + Objects.hash(o.key, o.val);
			return s1.compareTo(s2);
		}
		
	}
}
