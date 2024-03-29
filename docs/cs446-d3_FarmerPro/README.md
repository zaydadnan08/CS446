﻿**Prototype Demonstration**

**Metadata**

Group Name: FarmerPro Group Number: 22

**Team members:**

Anweshi Anavadya (aanavady) David Mehic (dmehic)

Hamza Saqib (h3saqib)

Kevin Liu (k267liu)

Shehryar Suleman (s6suleman) Zayd Adnan (zadnan)

**Github:** <https://github.com/zaydadnan08/CS446>

**Status**

The FarmerPro app is currently being developed by the team and has made some structural progress in addition to some UI updates as well. So far we have a splash page that is displayed when the app is booted up. Once the user clicks on the "get started" button displayed on the splash page, they are prompted to enter their user type. The user type is either a customer, farmer or a community fridge admin. After clicking on the user type, the user is then taken to a login screen where they are authenticated with the help of firebase.

After logging in, the user is then shown a page of the marketplace which displays all the products that are on sale and for the price at which they are being sold. This part is simulated for the demo to depict what it will essentially look like as the end product. As a farmer logging in, this will be different as they will be prompted to add the items they are willing to sell on the marketplace and for the cost of it. The community fridges page is also available to be viewed by the user and they are sorted by distance to the user. This is shown with the help of simulated data as well. The customers can view the storage levels of a community fridge and the items they have available. Once the community fridge admin role is developed then they can request for items from farmers. The requesting can also be done by the customers and the farmers will be able to view this.

So far our progress has been on authentication and some structural decisions. We have also spent effort on cleaning up the UI and making it minimalistic and straightforward for users to interact with. We are currently having some trouble with the implementation of the database and its structure that will intertwine with many aspects of our app. Once this issue is resolved, we will be able to make much better progress.

Our next steps will be focused on the perfected implementation of the database as it is an integral part of the system. While this development will be happening, we will also be working on the voice-to-text model that will then be integrated for the farmers to use when they are unable to have any wifi service. We will also add the functionality of a community fridge admin who will be able to manage the inventory and request items for their respective community fridges and to adequately manage its operation

**Component Diagram:**

This is our component diagram depicting the topology of our FarmerPro app that utilizes MVVM and repository architecture styles.

![](pic1.jpeg)
