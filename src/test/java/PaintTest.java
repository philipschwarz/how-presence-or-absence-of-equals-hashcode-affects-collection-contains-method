import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

import org.junit.Test;

public class PaintTest
{
	// ####################
	// # TreeSet, TreeMap #
	// ####################

	@Test
	public void if_an_object_has_DEFAULT_EQUALS_then_you_CAN_find_it_in_tree_sets()
	{
		// Passes because of the following in javadoc of TreeSet:

		// Note that the ordering maintained by a set (whether or not an explicit comparator is provided) must be consistent with equals if it is to
		// correctly implement the Set interface. (See Comparable or Comparator for a precise definition of consistent with equals.) This is so because
		// the Set interface is defined in terms of the equals operation, but a TreeSet instance performs all element comparisons using its compareTo
		// (or compare) method, so two elements that are deemed equal by this method are, from the standpoint of the set, equal. The behavior of a set
		// is well-defined even if its ordering is inconsistent with equals; it just fails to obey the general contract of the Set interface.

		TreeSet<Paint> treeSetOfPaints = new TreeSet<>();
		treeSetOfPaints.add(PAINT);
		assertTrue( treeSetOfPaints.contains(SAME_PAINT) );
	}

	@Test
	public void if_an_object_has_DEFAULT_EQUALS_then_you_CAN_use_it_as_a_key_in_tree_maps()
	{
		// Passes because of the following in javadoc of TreeMap:

		// Note that the ordering maintained by a set (whether or not an explicit comparator is provided) must be consistent with equals if it is to
		// correctly implement the Set interface. (See Comparable or Comparator for a precise definition of consistent with equals.) This is so because
		// the Set interface is defined in terms of the equals operation, but a TreeSet instance performs all element comparisons using its compareTo
		// (or compare) method, so two elements that are deemed equal by this method are, from the standpoint of the set, equal. The behavior of a set
		// is well-defined even if its ordering is inconsistent with equals; it just fails to obey the general contract of the Set interface.

		TreeMap<Paint,String> treeMap = new TreeMap<>();
		treeMap.put(PAINT, "something" );
		assertNotNull( treeMap.get(SAME_PAINT) );
	}
	
	// ############################
	// # LinkedList and ArrayList #
	// ############################ 
	
	@Test public void if_an_object_has_DEFAULT_EQUALS_then_you_CANNOT_find_it_in_LINKED_LISTS_and_ARRAY_LISTS() 
	{ 
		// Because when the list uses equals to compare the objects, equals returns false since they have different memory addresses

		LinkedList<Paint> linkedListOfpaints = new LinkedList<>();
		linkedListOfpaints.add(PAINT);
		assertFalse( linkedListOfpaints.contains(SAME_PAINT) );

		ArrayList<Paint> arrayListOfpaints = new ArrayList<>();
		arrayListOfpaints.add(PAINT);
		assertFalse( arrayListOfpaints.contains(SAME_PAINT) );
	}
	
	@Test public void if_an_object_overrides_DEFAULT_EQUALS_then_you_CAN_find_it_in_LINKED_LISTS_and_ARRAY_LISTS()
	{
		// Because when the list uses equals to compare the objects, equals returns true if the objects are equal

		LinkedList<PaintWithEquals> linkedListOfPaintsWithEquals = new LinkedList<>();
		linkedListOfPaintsWithEquals.add(PAINT_WITH_EQUALS);
		assertTrue( linkedListOfPaintsWithEquals.contains(SAME_PAINT_WITH_EQUALS) );

		ArrayList<PaintWithEquals> arrayListOfPaintsWithEquals = new ArrayList<>();
		arrayListOfPaintsWithEquals.add(PAINT_WITH_EQUALS);
		assertTrue( arrayListOfPaintsWithEquals.contains(SAME_PAINT_WITH_EQUALS) );
	}	
	
	// ######################
	// # HashMap, HashTable #
	// ###################### 	
	
	@Test
	public void if_an_object_has_DEFAULT_EQUALS_then_you_CANNOT_use_it_as_a_key_in_HASH_MAPS() 
	{
		// Because the hashcode function returns different hashcodes for equal objects (the hashcodes are based on memory address) so the objects
		// are either hashed to different buckets, or found to have different hashcodes despite being hashed to the same bucket.

		Map<Paint,String> map = new HashMap<>();
		map.put(PAINT, "something");
		assertNull(map.get(SAME_PAINT));
		
		map = new Hashtable();
		map.put(PAINT, "something");
		assertNull(map.get(SAME_PAINT));
	}

	@Test
	public void if_an_object_overrides_DEFAULT_EQUALS_then_you_STILL_CANNOT_use_it_as_a_key_in_HASH_MAPS() throws Exception
	{
		// Because the hashcode function returns different hashcodes for equal objects (the hashcodes are based on memory address) so the objects
		// are either hashed to different buckets, or found to have different hashcodes despite being hashed to the same bucket.
		// In the latter case, it is not enough for the two objects to be equal because hash maps compare hashCode before checking for equality

		Map<PaintWithEquals,String> map = new HashMap<PaintWithEquals,String>();
		map.put(PAINT_WITH_EQUALS, "something");
		assertNull(map.get(SAME_PAINT_WITH_EQUALS));
		
		map = new Hashtable();
		map.put(PAINT_WITH_EQUALS, "something");
		assertNull(map.get(SAME_PAINT_WITH_EQUALS));
	}

	@Test
	public void if_an_object_has_DEFAULT_EQUALS_but_overrides_DEFAULT_HASH_CODE_then_you_STILL_CANNOT_use_it_as_a_key_in_HASH_MAPS() throws Exception
	{
		// Because even though equal objects are hashed to the same bucket, when the map uses equals to compare the objects, equals returns false 
		// since they have different memory addresses
		Map<PaintWithHashCode,String> map = new HashMap<>();
		map.put(PAINT_WITH_HASH_CODE, "something");
		assertNull(map.get(SAME_PAINT_WITH_HASH_CODE));
		
		map = new Hashtable();
		map.put(PAINT_WITH_HASH_CODE, "something");
		assertNull(map.get(SAME_PAINT_WITH_HASH_CODE));
	}

	@Test
	public void if_an_object_overrides_DEFAULT_EQUALS_AND_HASH_CODE_BUT_HASH_CODE_IS_INVALID_then_you_STILL_CANNOT_use_it_as_a_key_in_HASH_MAPS() throws Exception
	{
		// Because the hashcode function returns different hashcodes for equal objects (which is invalid) so the objects are hashed to different buckets.
		Map<PaintWithEqualsAndInvalidHashCode,String> map = new HashMap<>();
		map.put(PAINT_WITH_EQUALS_AND_INVALID_HASH_CODE, "something");
		assertNull(map.get(SAME_PAINT_WITH_EQUALS_AND_INVALID_HASH_CODE));
		
		map = new Hashtable();		
		map.put(PAINT_WITH_EQUALS_AND_INVALID_HASH_CODE, "something");
		assertNull(map.get(SAME_PAINT_WITH_EQUALS_AND_INVALID_HASH_CODE));
	}

	@Test
	public void if_an_object_overrides_DEFAULT_EQUALS_AND_HASH_CODE_then_you_CAN_use_it_as_a_key_in_HASH_MAPS() throws Exception
	{
		// Because equal objects are hashed to the same bucket and they have the same hash code and equals returns true
		Map<PaintWithEqualsAndHashCode,String> map = new HashMap<>();
		map.put(PAINT_WITH_EQUALS_AND_HASH_CODE, "something");
		assertNotNull(map.get(SAME_PAINT_WITH_EQUALS_AND_HASH_CODE));
		
		map = new Hashtable();	
		map.put(PAINT_WITH_EQUALS_AND_HASH_CODE, "something");
		assertNotNull(map.get(SAME_PAINT_WITH_EQUALS_AND_HASH_CODE));
	}

	// ###########
	// # HashSet #
	// ########### 	
	@Test
	public void if_an_object_has_DEFAULT_EQUALS_then_you_CANNOT_find_it_in_hash_sets()
	{
		HashSet<Paint> hashSet = new HashSet<>();
		hashSet.add(PAINT);
		assertFalse( hashSet.contains(SAME_PAINT) );
	}
	
	@Test public void if_an_object_overrides_DEFAULT_EQUALS_then_you_STILL_CANNOT_find_it_in_hash_sets()
	{
		HashSet<PaintWithEquals> hashSet = new HashSet<>();
		hashSet.add(PAINT_WITH_EQUALS);
		assertFalse( hashSet.contains(SAME_PAINT_WITH_EQUALS) );
	}

	@Test public void if_an_object_has_DEFAULT_EQUALS_but_overrides_DEFAULT_HASH_CODE_then_you_STILL_CANNOT_find_it_in_hash_sets()
	{
		HashSet<PaintWithHashCode> hashSet = new HashSet<>();
		hashSet.add(PAINT_WITH_HASH_CODE);
		assertFalse( hashSet.contains(SAME_PAINT_WITH_HASH_CODE) );
	}

	@Test public void if_an_object_overrides_DEFAULT_EQUALS_AND_HASH_CODE_BUT_HASH_CODE_IS_INVALID_then_you_STILL_CANNOT_find_it_in_hash_sets()
	{
		HashSet<PaintWithEqualsAndInvalidHashCode> hashSet = new HashSet<>();
		hashSet.add(PAINT_WITH_EQUALS_AND_INVALID_HASH_CODE);
		assertFalse( hashSet.contains(SAME_PAINT_WITH_EQUALS_AND_INVALID_HASH_CODE) );
	}
	
	@Test public void if_an_object_overrides_DEFAULT_EQUALS_AND_HASH_CODE_then_you_CAN_find_it_in_hash_sets()
	{
		HashSet<PaintWithEqualsAndHashCode> hashSet = new HashSet<>();
		hashSet.add(PAINT_WITH_EQUALS_AND_HASH_CODE);
		assertTrue( hashSet.contains(SAME_PAINT_WITH_EQUALS_AND_HASH_CODE) );
	}	
	
	private static final Paint PAINT = new Paint(1.5,20,40,40);
	private static final Paint SAME_PAINT = new Paint(1.5,20,40,40);

	private static final PaintWithEquals PAINT_WITH_EQUALS = new PaintWithEquals(1.5,20,40,40);
	private static final PaintWithEquals SAME_PAINT_WITH_EQUALS = new PaintWithEquals(1.5,20,40,40);

	private static final PaintWithEqualsAndHashCode PAINT_WITH_EQUALS_AND_HASH_CODE = new PaintWithEqualsAndHashCode(1.5,20,40,40);
	private static final PaintWithEqualsAndHashCode SAME_PAINT_WITH_EQUALS_AND_HASH_CODE = new PaintWithEqualsAndHashCode(1.5,20,40,40);

	private static final PaintWithHashCode PAINT_WITH_HASH_CODE = new PaintWithHashCode(1.5,20,40,40);
	private static final PaintWithHashCode SAME_PAINT_WITH_HASH_CODE = new PaintWithHashCode(1.5,20,40,40);

	private static final PaintWithEqualsAndInvalidHashCode PAINT_WITH_EQUALS_AND_INVALID_HASH_CODE = new PaintWithEqualsAndInvalidHashCode(1.5,20,40,40);
	private static final PaintWithEqualsAndInvalidHashCode SAME_PAINT_WITH_EQUALS_AND_INVALID_HASH_CODE = new PaintWithEqualsAndInvalidHashCode(1.5,20,40,40);
}
