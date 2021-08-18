# **Android**

---

## Core Android

---

`Activity, Intents, Fragments, Broadcast Recv, Services`

 `Activity`

- An *activity* represents a single screen in your app with an interface the user can interact with
- <img src="https://google-developer-training.github.io/android-developer-fundamentals-course-concepts-v2/images/2-2-c-activity-lifecycle-and-state/basic-lifecycle.png" alt=" Diagram of the App Lifecycle" style="zoom: 80%;" />
- `onCreate()` is called only once
- `onSavedInstanceState()` - This method is used to store data before pausing the activity.
- `onRestoreInstanceState()` - This method is used to recover the saved  state of an activity when the activity is recreated after destruction.  So, the `onRestoreInstanceState()` receive the bundle that contains the  instance state information.

`Intents`

- Messaging service that is used to communicate between various components of the Android application. For example, if you want to send some  message from Delhi to Mumbai using the Post Office facility then you can do so by buying an Envelope and then pass the message in the Envelope  and send the message to the desired location
- [`Intent`](https://developer.android.com/reference/android/content/Intent.html), which is a message object that makes a request to the Android runtime  to start an activity or other app component in your app or in some other app
  - *Explicit intent:* You specify the receiving activity (or  other component) using the activity's fully qualified class name. You  use explicit intents to start components in your own app (for example,  to move between screens in the UI), because you already know the package and class name of that component.
  - *Implicit intent:* You do *not* specify a specific  activity or other component to receive the intent. Instead, you declare a general action to perform, and the Android system matches your request  to an activity or other component that can handle the requested action.  You learn more about using implicit intents in another practical.
- If you want someone to perform any Intent operation at future point of time on behalf of you, then we will use Pending Intent.

`Fragment`

- <img src="https://s3.ap-south-1.amazonaws.com/mindorks-server-uploads/android_fragments_lifecycle_mindorks_image.png" alt="Android  Fragments and its Lifecycle" style="zoom: 80%;" />

  ***When fragment come up on the screen:-***

  1. onAttach() — This method called first, To know that our fragment has been attached to an  activity. We are passing the Activity that will host our fragment. 
  2. onCreate() —  This method called when a fragment instance initializes, just after the onAttach where fragment attaches to the host activity.
  3. onCreateView() —  The method called when it’s time for the fragment to draw its user  interface for the first time. To draw a UI for your fragment, you must  return a View component from this method that is the root of your  fragment’s layout. You can return null if the fragment does not provide a UI.
  4. onActivityCreated() — This method called when Activity completes its onCreate() method
  5. onStart() — This method called when a fragment is visible.
  6. onResume() — This method called when a fragment is visible and allowing the user to  interact with it. Fragment resumes only after activity resumes.

  ***When fragment goes out off the screen:-***

  1. onPause() — This method called when a fragment is not allowing the user to interact; the fragment will get change with other fragment or it gets removed from  activity or fragment’s activity called a pause.
  2. onStop() — This  method called when the fragment is no longer visible; the fragment will  get change with other fragment or it gets removed from activity or  fragment’s activity called stop.
  3. onDestroyView() —  This method  called when the view and related resources created in onCreateView() are removed from the activity’s view hierarchy and destroyed.
  4. onDestroy() —  This method called when the fragment does its final clean up.
  5. onDetach() —  This method called when the fragment is detached from its host activity.

`Broadcasts`

-  Broadcasts are sent when an event of interest occurs. For example, the Android system sends broadcasts when various system events occur, such as when the system boots up or the device starts charging. Apps can also send custom broadcasts, for example, to notify other apps of something that they might be interested in (for example, some new data has been downloaded).
- If the communication is not between different applications on the  Android device then is it suggested not to use the Global  BroadcastManager because there can be some security holes while using  Global Broadcastmanager, LocalBroadcastManager is used to  register and send a broadcast of intents to local objects in your process.

`Services`

- Perform long-running operations in the background. It does not provide a user interface. Once started, a service might continue running for some time, even after the user switches to another application. Additionally, a component can bind to a service to interact with it and even perform interprocess communication (IPC). For example, a service can handle network transactions, play music, perform file I/O, or interact with a content provider, all from the background
- Foreground, Background, Bound
- Whenever a client sends a request then the Service will be started and  after handling each and every Intent, the Service will be stopped.  Clients can send the request to start a Service by using **Context.startService(Intent)**. Here, a worker thread is created and all requests are handled using the worker thread but at a time, only one request will be processed
- Communicating with MainThread - Service else IntentService

``

`Shared Prerences`

- Stores data in key/value pairs
- Data is persisted across all the sessions
- commit() returns a boolean value of success or failure immediately by writing data synchronously
- apply() is asynchronous and it won't return any boolean response. If you have an apply() outstanding and you are performing commit(), then  the commit() will be blocked until the apply() is not completed

`Scoped Storage`

- Give access to what is needed and not all the storage access
- Manage app specific storage accordingly say when user uninstalls the app

`Constraint Layout`

- Create responsive user interface layouts that adapt automatically to different screen sizes and changing device orientations
- benefit of avoiding many problems inherent in nesting layouts
- Screen Adaptability

`WorkManger vs JobScheduler`

`WorkManager` uses `JobScheduler` service to schedule the jobs. If `JobScheduler` is not supported by the device, then it uses Firebase `JobDispatcher` service. If Firebase `JobDispatcher` is not available on the device, it will use `AlarmManager` and `BroadcastReceiver`.