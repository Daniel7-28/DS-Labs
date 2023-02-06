//public class test {
//	
//	public static boolean listsAreEqual(Lab06P1Wrapper.DoublyLinkedList<String> list1, Lab06P1Wrapper.DoublyLinkedList<String> list2) {
//		if (list1.size() != list2.size()) {
//			return false;
//		}
//		for (int i = 0; i < list1.size(); ++ i) {
//			if (!list1.get(i).equals(list2.get(i))) {
//				return false;
//			}
//		}
//		
//		return true;
//	}
//	public static void main(String[] args) {
//		Lab06P1Wrapper.DoublyLinkedList<String> l1 = new Lab06P1Wrapper.DoublyLinkedList<String>();
//		Lab06P1Wrapper.DoublyLinkedList<String> l2 = new Lab06P1Wrapper.DoublyLinkedList<String>();
//		Lab06P1Wrapper.DoublyLinkedList<String> test1 = new Lab06P1Wrapper.DoublyLinkedList<String>();
//		Lab06P1Wrapper.DoublyLinkedList<String> test2 = new Lab06P1Wrapper.DoublyLinkedList<String>();
//		l1.add("Bob");
//		l1.add("Tina");
//		l1.add("Forrest");
//		l1.add("Mel");
//		l1.add("Tina");
//		
//		l2.add("Bob");
//		l2.add("Tina");
//		l2.add("Forrest");
//		l2.add("Mel");
//		l2.add("Tina");
//		
//		test1.add("Tina");
//		test1.add("Forrest");
//		test1.add("Mel");
//		test1.add("Tina");
//		
//		
//		test2.add("Bob");
//		test2.add("May");
//		test2.add("Forrest");
//		test2.add("Mel");
//		test2.add("May");
//		
//		l1.remove(0);
//		if (listsAreEqual(l1, test1)) {
//			System.out.println("Test 1 passed");
//		} else {
//			System.out.println("Test 1 failed");
//		}
//		l2.replaceAll("Tina", "May");
//		if (listsAreEqual(l2, test2)) {
//			System.out.println("Test 2 passed");
//		} else {
//			System.out.println("Test 2 failed");
//		}
//		
//	}
//}