# **Languages**

---

## **Kotlin**

---

- `Why Kotlin?`

  - Concise and fast ship
  - null-safe
  - interoperable aka call java code
  - First class and backed by Google

- `var vs val vs const` - `val` is same as the `final` modifier in java. As you should probably know that we can not assign to a `final` variable again but can change its properties. *value of the `val` variable is initialized at runtime and `const` at compile time*

- `static method in kotlin` - Static variables or methods ***belong to a class and not to its instance***. - https://blog.mindorks.com/what-is-the-equivalent-of-java-static-methods-in-kotlin

  ```java
  class CompanyUtils {
      companion object myCompanionName {
          fun getCompanyAddress(): String {
              return "MindOrks, G-773, GROUND FLOOR SUNCITY, SECTOR-54 GURUGRAM, HR"
          }
      }
  }
  ```

- `Data Class` -  

  - The primary constructor needs to have at least one parameter.
  - All primary constructor parameters need to be marked as val or var
  - Data classes cannot be abstract, open, sealed, or inner.

  ```java
  data class Developer(val name: String, val age: Int)
  ```

- `Sealed classes` - 

  

- `Kotlin collections` - 

  <img src="T:\git_gorishabh\Private\Interview_Prep\image-20210817153503220.png" alt="image-20210817153503220" style="zoom:50%;" />

- `lateint & lazy` - 

  - The `lateinit` keyword is used for late initialization of variables. To use a `lateinit` variable, your variable should use `var` and NOT `val`. `lateinit` is allowed for non-primitive data types only and the variable  can't be of null type. Also, `lateinit` variable can be declared either inside the class or it can be a top-level property.

    ```java
    private lateinit var courseName: String
    // demo function to get course name using the courseId
    fun fetchCourseName(courseId: String) {
        courseName = courseRepository.getCourseName(courseId)
        // this is an example, you can add other suff according to your usecase
    }
    
    
    // If accessed without initialization [ UninitializedPropertyAccessException: lateinit property courseName has not been initialized ]
    ```

  - The benefit of using `lazy` is that the object will be created only when it is called otherwise it will not be created and you will use the same object again when called.

  ```java
  class SomeClass {
       private val heavyObject: HeavyClass by lazy {
          HeavyClass()
      }  
  }
  ```

- `Kotlin Coroutines` -  https://blog.mindorks.com/mastering-kotlin-coroutines-in-android-step-by-step-guide

  Coroutines, a very efficient and complete framework to manage concurrency in a more performant and simple way. *One can think of a coroutine as a light-weight  thread. Like threads, coroutines can run in parallel, wait for each  other and communicate. The biggest difference is that coroutines are  very cheap, almost free: we can create thousands of them, and pay very  little in terms of performance. True threads, on the other hand, are  expensive to start and keep around. A thousand threads can be a serious  challenge for a modern machine.*

  - `launch`: fire and forget
  - `async`: perform a task and return a result

  Scopes in Kotlin Coroutines are very useful because we need to cancel the background task as soon as the activity is destroyed

  

- `Kotlin Flow` - In Kotlin, **Coroutine is just the scheduler part of RxJava but now with  Flow APIs coming along side it**, it can be alternative to RxJava in  Android.

  In RxJava, Observables type is an example of a structure that represents a stream of items. Its body does not get executed until it is  subscribed to by a subscriber. and once it is subscribed, subscriber  starts getting the data items emitted. Similarly, Flow works on the same condition where the code inside a *flow* builder does not run until the *flow* is collected.

  

- `Kolin null-safe` -  In Kotlin, a normal property can’t hold a null value -> https://blog.mindorks.com/safecalls-vs-nullchecks-in-kotlin

  **The basic difference between the Safe call and  Null check is that we use Null checks (!!) only when we are confident  that the property can’t have a null value. And if we are not sure that  the value of the property is null or not then we prefer to use Safe  calls(?.).**

  ```java
  var name: String = "MindOrks"
  name = null //compilation err
  
  var name: String? = "MindOrks" //no error
  name = null //no error
  ```

- **`let`, `run`, `with`, `also`, `apply`**  in Kotlin are scoped functions ( *functions that execute a block of code within the context of an object, context of the object can be referred to as “it” or “this”* ) -> https://blog.mindorks.com/using-scoped-functions-in-kotlin-let-run-with-also-apply

  ​									![Using Scoped Functions in Kotlin - let, run, with, also, apply](https://s3.ap-south-1.amazonaws.com/mindorks-server-uploads/using-scoped-functions-summary.png) 							

  - **let** - 

    ```java
    private fun performLetOperation() {
        val person = Person().let {
            return@let "The name of the Person is: ${it.name}"
        }
        print(person)
    }
    output:
    The name of the Person is: Abcd
    ```

  - **run** -

    ```java
    private fun performRunOperation() {
        Person().run {
            name = "Asdf"
            contactNumber = "0987654321"
            return@run "The details of the Person is: ${displayInfo()}"
        }
    }
    output:
     Name: Asdf
     Contact Number: 0987654321
     Address: xyz
    ```

  - #### run vs let

    So if **run** is similar to **let** in terms of accepting any return value, what’s the difference? The difference is run refers to the context of the object as “**this**” and not “**it**”.

  - **with** - 

    ```java
    private fun performWithOperation() {
        val person = with(Person()) {
            return@with "The name of the Person is: ${this.name}"
        }
        print(person)
    }
    Output:
    The name of the Person is: Abcd
    ```

  - #### with vs run

    So, if “**with”** is the same as “**run**”, what’s the difference? How should we choose between these two? Now,  this is an interesting case. Let’s consider a case where a Person object can be nullable.

    ​									![Using Scoped Functions in Kotlin - let, run, with, also, apply](https://s3.ap-south-1.amazonaws.com/mindorks-server-uploads/using-scoped-functions-with-nullable-value.png) 							

    we can see that the context of the object referred to as “**this**” is a nullable type of Person. And hence, to correct this, we need to change the code as:

    ```java
    private fun performWithOperation() {
        val person: Person? = null
        with(person) {
            this?.name = "asdf"
            this?.contactNumber = "1234"
            this?.address = "wasd"
            this?.displayInfo()
        }
    }
    ```

    So performing a null check using a “**with**” operator is difficult and this is where we can replace it with “**run**” as follows:

    ```java
    private fun performRunOperation() {
        val person: Person? = null
        person?.run {
            name = "asdf"
            contactNumber = "1234"
            address = "wasd"
            displayInfo()
        }
    }
    ```

    This looks a lot cleaner.

  - **apply** -

    ```java
    private fun performApplyOperation() {
        val person: Person? = null
        person?.apply {
            name = "asdf"
            contactNumber = "1234"
            address = "wasd"
            displayInfo()
        }
    }
    ```

  - #### apply vs run

    ![Using Scoped Functions in Kotlin - let, run, with, also, apply](https://s3.ap-south-1.amazonaws.com/mindorks-server-uploads/using-scoped-functions-apply-vs-run.png) 							

    We can see that **run** accepts a return statement whereas **“**apply” does not accept a return statement(we can see the error thrown by the  IDE in the image) and always returns the same object which it is  referring to.

  - **also** -

    ```java
    private fun performAlsoOperation() {
        val name = Person().also { currentPerson ->
            print("Current name is: ${currentPerson.name}\n")
            currentPerson.name = "modifiedName"
        }.run {
            "Modified name is: $name\n"
        }
        print(name)
    }
    output:
    Current name is: Abcd
    Modified name is: modifiedName
        
    // with let    
    var name = Person().name
    print("Current name is: $name\n")
    name = "modifiedName"
    name = name.run {
        "Modified name is: $name\n"
    }
    print(name)
    ```

  - also vs let

    ​									![Using Scoped Functions in Kotlin - let, run, with, also, apply](https://s3.ap-south-1.amazonaws.com/mindorks-server-uploads/using-scoped-functions-also-vs-let.png)



------

---

---



## **Java**

---

`OOPS`

Object Oriented programming (OOP) is a programming paradigm that relies on the concept of **classes** and **objects**. { APIE }

- ***In inheritance relationship, when we create an object of a  child class, then first base class constructor and then derived class  constructor get called implicitly.***

- **Difference between method overloading and overriding.**

  [![Overloading and Overriding](https://github.com/codeshef/android-interview-questions/raw/master/assets/overloading-vs-overriding.png)](https://github.com/codeshef/android-interview-questions/blob/master/assets/overloading-vs-overriding.png)

  

  - Overloading happens at compile-time while Overriding happens at  runtime: The binding of overloaded method call to its definition happens at compile-time however binding of overridden method call to its  definition happens at runtime. More info on static vs. dynamic binding: [StackOverflow](https://stackoverflow.com/questions/19017258/static-vs-dynamic-binding-in-java).

  - Static methods can be overloaded which means a class can have more  than one static method of same name. Static methods cannot be  overridden, even if you declare a same static method in child class it  has nothing to do with the same method of parent class as overridden  static methods are chosen by the reference class and not by the class of the object.

    So, for example:

    ```
    public class Animal {
        public static void testClassMethod() {
            System.out.println("The static method in Animal");
        }
    
        public void testInstanceMethod() {
            System.out.println("The instance method in Animal");
        }
    }
    
    public class Cat extends Animal {
        public static void testClassMethod() {
            System.out.println("The static method in Cat");
        }
    
        public void testInstanceMethod() {
            System.out.println("The instance method in Cat");
        }
    
        public static void main(String[] args) {
            Cat myCat = new Cat();
            myCat.testClassMethod();
            Animal myAnimal = myCat;
            myAnimal.testClassMethod();
            myAnimal.testInstanceMethod();
        }
    }
    ```

    Will output:

    ```
    The static method in Cat    // testClassMethod() is called from "Cat" reference
    
    The static method in Animal // testClassMethod() is called from "Animal" reference,
                                // ignoring actual object inside it (Cat)
    
    The instance method in Cat  // testInstanceMethod() is called from "Animal" reference,
                                // but from "Cat" object underneath
    ```

    The most basic difference is that ***overloading is being done in the  same class while for overriding base and child classes are required***.  Overriding is all about giving a specific implementation to the  inherited method of parent class.

    *Static binding is being used for overloaded methods and dynamic  binding is being used for overridden/overriding methods. Performance: Overloading gives better performance compared to  overriding. The reason is that the binding of overridden methods is  being done at runtime.*

    **Private and final methods can be overloaded but they cannot be  overridden**. It means a class can have more than one private/final  methods of same name but a child class cannot override the private/final methods of their base class.

    **Return type of method does not matter in case of method overloading,  it can be same or different. However in case of method overriding the  overriding method can have more specific return type** (meaning if, for  example, base method returns an instance of Number class, all overriding methods can return any class that is extended from Number, but not a  class that is higher in the hierarchy, like, for example, Object is in  this particular case).

    **Argument list should be different while doing method overloading. Argument list should be same in method Overriding**. It is also a good practice to annotate overridden methods with `@Override` to make the compiler be able to notify you if child is, indeed, overriding parent's class method during compile-time.

- **Differences between abstract classes and interfaces?**
  - An abstract class, is a class that contains both concrete and abstract methods (methods without implementations). An abstract method must be implemented by the abstract class sub-classes. Abstract classes cannot be instantiated and need to be extended to be used.
  - An interface is like a blueprint/contract of a class (or it may be  thought of as a class with methods, but without their implementation).  It contains empty methods that represent, what all of its subclasses should have in common. The  subclasses provide the implementation for each of these methods. Interfaces are implemented.
- Although nowadays, we all use [RxJava](https://blog.mindorks.com/rxjava-anatomy-what-is-rxjava-how-rxjava-is-designed-and-how-rxjava-works-d357b3aca586), [Kotlin-Coroutines](https://blog.mindorks.com/mastering-kotlin-coroutines-in-android-step-by-step-guide) for background task execution, it is important to know about the  ThreadPoolExecutor as all of these libraries are made on top of  ThreadPoolExecutor.
  - A thread pool manages a pool of worker threads (the exact number varies depending upon its implementation). A task queue holds tasks waiting to be executed by anyone of the idle  threads in the pool. Tasks are added to the queue by producers, whereas  the worker threads act as consumers by consuming the tasks from the  queue whenever there’s an idle thread ready to perform a new background  execution.
- this and super() both are Reerved keywords in java
  - The `this` keyword points to a reference of the current class, while the `super` keyword points to a reference of the parent class. `this` can be used to access variables and methods of the current class, and `super` can be used to access variables and methods of the parent class from the subclass.
- **What is garbage collector? How does it work?**
  - All objects are allocated on the heap area managed by the JVM. As long as an object is being referenced, the JVM considers it alive. Once an object is no longer referenced and therefore is not reachable by the application code, the garbage collector removes it and reclaims the unused memory.
- `Volatile vs Trasient` - 

  - **Volatile** - volatile variables are written to and read from main memory
  - **Transient** - when we do not want an object to be serialized we can use a **transient** keyword

