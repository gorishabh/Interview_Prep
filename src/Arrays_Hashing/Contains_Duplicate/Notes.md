# Learnings

- Brute force and sorting but not optimal
- Using hash set we can achieve optimal solution
- No direct way to convert primitive array to set so better to ask interviewer or let non-primitive type always
  - If you start with non-primitive type it will save time
  - Can you use collections/methods to create map # Arrays.asList(List<Integer>)
  - HashSet<Integer> set = new HashSet<>(Arrays.asList(List<Integer>))
  - .size() for length of LISTS/SET