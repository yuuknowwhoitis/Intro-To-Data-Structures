

public class MyHashtable implements DictionaryInterface {

    protected int tableSize;
    protected int size;
    protected MyLinkedList[] table;

    protected class Entry{
        String key;
        Object value;

        Entry(String k, Object o){
            key = k;
            value = o;
        }
    }

    public MyHashtable(int tableSize){
        this.tableSize = tableSize;
        size = 0;
        table = new MyLinkedList[tableSize];
    }

    //checks if table is empty
    public boolean isEmpty() {
        if(size == 0){
            return true;
        }
        else{
            return false;
        }
    }

    //returns size
    public int size() {
        return size;
    }

    //empty table
    public void clear() {
        table = new MyLinkedList[tableSize];
        size = 0;
    }

    //return corresponding entries for a given key
    public Object get(String key) {
        int hashCode = key.hashCode();
        int arrayIndex = Math.abs(hashCode) % tableSize;

        if(table[arrayIndex] == null){
            return null;
        }
        else{
            MyLinkedList bucket = (MyLinkedList) table[arrayIndex];
            for(int i = 0; i < bucket.size; i++){
                Entry temp = (Entry) bucket.get(i);
                if(temp.key.equals(key)){
                    return temp.value;
                }
            }

        }
        return null;
    }

    //enter new entry
    public Object put(String key, Object value) {
        int hashcode = key.hashCode();
        int arrayIndex = Math.abs(hashcode) % tableSize;

        if(table[arrayIndex] == null){
            MyLinkedList bucket = new MyLinkedList();
            Entry entry = new Entry(key, value);
            bucket.add(0, entry);
            size++;
            table[arrayIndex] = bucket;
            return null;
        }
        else{
            Boolean found = false;
            MyLinkedList bucket = (MyLinkedList) table[arrayIndex];
            for(int i = 0; i < bucket.size; i++){
                Entry temp = (Entry) bucket.get(i);
                if(temp.key.equals(key)){
                    bucket.remove(i);
                    bucket.add(i, new Entry(key, value));
                    return temp;
                }
                else{
                    bucket.add(0, new Entry(key, value));
                    size++;
                    return null;
                }
            }
        }
        return null;
    }

    //remove entry
    public void remove(String key) {
        int hashcode = key.hashCode();
        int arrayIndex = Math.abs(hashcode) % tableSize;

        if(table[arrayIndex] == null){

        }
        else{
            MyLinkedList bucket = (MyLinkedList) table[arrayIndex];
            for(int i = 0; i < bucket.size; i++){
                Entry temp = (Entry) bucket.get(i);
                if(temp.key.equals(key)){
                    bucket.remove(i);
                    size--;
                }
            }
        }
    }

    //returns array of all keys
    public String[] getKeys() {

        String[] keys = new String[size];
        int index = 0;
        for(int i = 0; i < tableSize; i++){
            if(table[i] != null) {
                for (int j = 0; j < table[i].size; j++) {
                    Entry temp = (Entry) table[i].get(j);
                    keys[index] = temp.key;
                    index++;
                }
            }
        }

        return keys;
    }

    // Returns the size of the biggest bucket (most collisions) in the hashtable.
	public int biggestBucket() {
		int biggestBucket = 0; 
		for(int i = 0; i < table.length; i++) {
			// Loop through the table looking for non-null locations. 
			if (table[i] != null) {
				// If you find a non-null location, compare the bucket size against the largest
				// bucket size found so far. If the current bucket size is bigger, set biggestBucket
				// to this new size. 
				MyLinkedList bucket = table[i];
				if (biggestBucket < bucket.size())
					biggestBucket = bucket.size();
			}
		}
		return biggestBucket; // Return the size of the biggest bucket found. 
	}

	// Returns the average bucket length. Gives a sense of how many collisions are happening overall.
	public float averageBucket() {
		float bucketCount = 0; // Number of buckets (non-null table locations)
		float bucketSizeSum = 0; // Sum of the size of all buckets
		for(int i = 0; i < table.length; i++) {
			// Loop through the table 
			if (table[i] != null) {
				// For a non-null location, increment the bucketCount and add to the bucketSizeSum
				MyLinkedList bucket = table[i];
				bucketSizeSum += bucket.size();
				bucketCount++;
			}
		}

		// Divide bucketSizeSum by the number of buckets to get an average bucket length. 
		return bucketSizeSum/bucketCount; 
	}


    public String toString() {
        String s = "";
        for(int tableIndex = 0; tableIndex < tableSize; tableIndex++) {
           // System.out.println(tableIndex);
           // System.out.println("Size " + tableSize);
            if (table[tableIndex] != null) {
                MyLinkedList bucket = table[tableIndex];
                for(int listIndex = 0; listIndex < bucket.size(); listIndex++) {
                    Entry e = (Entry)bucket.get(listIndex);
                    s = s + "key: " + e.key + ", value: " + e.value + "\n";
                }
            }
        }
        return s;
    }
}