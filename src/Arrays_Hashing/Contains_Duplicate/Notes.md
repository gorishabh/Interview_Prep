# Learnings

- Brute force and sorting but not optimal
- Using hash set we can achieve optimal solution
- No direct way to convert primitive array to set so better to ask interviewer or let non-primitive type always
  - If you start with non-primitive type it will save time
  - Can you use collections/methods to create map # Arrays.asList(List<Integer>)
  - HashSet<Integer> set = new HashSet<>(Arrays.asList(List<Integer>))
  - .size() for length of LISTS/SET

### We can fail fast in the hashset approach by checking if the element is already in the set while creating the set itself

```java
class Solution {
  public boolean containsDuplicate(int[] nums) {
    HashSet<Integer> seen = new HashSet<>();
    for (int num : nums) {
      if (seen.contains(num))
        return true;
      seen.add(num);
    }
    return false;
  }
}
```