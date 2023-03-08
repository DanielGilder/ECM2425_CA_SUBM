# ECM2425_CA_V2

AUTHOR: Daniel Gilder

INTRODUCTION:

    -This project is an Android Mobile App developed in the Java Android SDK. The application is a recipe note saving app that allows
     users to save their recipes that peek their interest and view it for later.

     -The apps specification:
        1. Multiple Activities : COMPLETED
            EXAMPLE: MainActivity, RecipeList, RecipePage

        2. Menu : COMPLETED
            EXAMPLE: Menu button displayed on the MainActivity and RecipeList

        3. Explicit and Implicit Intents : COMPLETED
            EXAMPLE: Explicit intents used to move from MainActivity to RecipeList and vice versa, and also from the RecipeList to RecipePage
                     Implicit intents are used to open links from the RecipePage to the devices web browser.

        4. Use Internet : COMPLETED
            EXAMPLE: The app provides networking operations by fetching images from URLs and displaying them on the RecipeList and RecipePage activity.

        5. Use RecyclerView/s : COMPLETED
            Example: The RecipeList activity utilises a RecyclerView component to  display a list of the users saved recipes.

        6. Use Data storage : COMPLETED
            Example: The app utilises an SQLite database to store the uses desired recipes locally to be displayed on the UI for later.

     -Testing Devices used:
        1. Pixel 4 XL (Virtual Device)
        2. Pixel 3a (Virtual Device)
        3. Samsung A52 (Physical Device)

DESIGN RATIONALE:

    -A. Data Storage:
        -For the data storing method I chose to make use of a SQLite Database provided by Androids libraries for the following the reasons:
            1. SQLite can perform faster and more efficient queries than SharedPreferences, especially when a large set of items that needs
               to be searched through is present.
            2. SQLite can handle a larger amount of data than SharedPreferences, which are aimed for storing little amount of data.
            3. SQLite can provide more security and integrity features than SharedPreferences, such as encryption, transactions and triggers.
            4. SQLite can store more complex and flexible data structures than SharedPreferences, which can only store key-value pairings.

        -The SQLite DB stores the following fields: (name TEXT primary key, imageURL TEXT,recipeURL TEXT, description TEXT)

    -B. Activity Layouts:
        -My Design Rationale for utilising a relative layout on the MainActivity is:
            1. It allows you to position your views based on their relationships with one another and with the parent container, which gives you more flexibility and control   over the layout design.
            2. It can eliminate nested view groups and keep your layout hierarchy flat, which improves performance by reducing memory usage and rendering time.
            3. It can make your code more readable and maintainable by using descriptive attributes such as `android:layout_above`, `android:layout_below`, `android:layout_toLeftOf`, etc. instead of hard-coded values or margins.
            4. It can enhance the usability of your app by adapting to different screen sizes and orientations without requiring extra layouts or adjustments.

        -My Design Rationale for utilising a Linear layout for Recipe entries and Recipe page are:
            1. It is the most basic layout in android studio, that allows you to arrange your views sequentially by specifying the android:orientation attribute, which makes it simple and easy to use.
            2. It aligns all the children in a single direction, either horizontally or vertically, which makes it consistent and predictable.
            3. It provides attributes such as android:gravity and android:layout_gravity to control the alignment of the views within the layout and within their parent container, which makes it flexible and adaptable.
            4. It allows you to assign weights to the views using the android:layout_weight attribute, which determines how much space each view occupies in relation to other views in the layout, which makes it efficient and responsive.

    C. Colour Scheme:
        -My reasons for choosing the colours Dark Green, Light Green and Black for the UI design:
            1. Dark Green and Light Green can create a natural and fresh look for the app, which can appeal to food lovers who care about health and quality.
            2. Dark Green and Light Green can also contrast well with white fonts or icons, making the app more readable and user-friendly.
            3. Black can be used as a background or accent colour to create a sleek and modern design for the app, which can attract more users who value professionalism and functionality.
            4. Black can also balance out the brightness of the green colours, making the app more visually pleasing and harmonious.

NOVEL FEATURES:
    -A novel feature that I added to the application which was not asked for by specification was a data entry page.
    -On the MainActivity users can enter and submit their data (Recipe data) using 4 text fields and a submit and button.

CHALLENGES FACED:
    -The original plan was to develop the app using the SharedPreferences feature for the application storage,I spent many hours trying to get the SharedPreferences
     to work with in the app and by the time I did, the SharedPreferences would create occasional errors or be unresponsive or slow, I wasn't sure if this was due to my
     AVD or my code, however it expended a vast amount of my free and development time. Which is one of the reasons why I had to resort to the SQLite
     method.

FUTURE IMPROVEMENTS:
    -Bugs:
        1. This isn't so much of a bug, but rather a latency issue, when the app displays the fetched image from the URL, there is sometimes a delay in displaying
            the image. However this could be potentially improved with better programming methods.

        2. Users can enter recipes with at least 1 data field, this can be a potential waste of memory as proper recipe item isn't being saved

    -Potential new features:
        1. With the current boom in AI tech in 2023, It would be an interesting feature to allow users to enter their food preferences and generate
           a custom recipe using an AI model using with an API from openAI and save that recipe for later.
        2. Allow users to organise their saved recipes by custom categories.
        3. Allow users to delete their saved recipes.
        4. Create a database on remote server and provide a login/signup feature, so users can access their recipes from various devices.

    -Security Flaws:
        -If an unauthorized user managed to hijack the application in person or remotely they could enter malicious URLs into the saved recipes
        prompt that take the user to dangerous web-pages rather than a legitimate recipe page, this can be achieved very easily with tools such as
        BeEF(Browser Exploitation Framework), that hackers use to create links to fake webpages that add JS hooks to your web browser allowing the hijacker to do serious harm to your privacy
        and security. Hijackers could log your key-strokes and save your password, they can even control what webpages you access in real time.
        So this is major security flaw that should be addressed.
