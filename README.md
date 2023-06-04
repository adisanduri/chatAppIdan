# chatAppIdan

This is a mobile application for chat functionality, developed in Java using Android Studio.

## Features

- Login with Google authentication
- Chat screen to display messages
- Messages include profile picture, name, and content
- Differentiation of user's own messages from others
- Push notifications for each message sent
- Automatic scrolling to the last received message
- Integration with Firebase for data storage and analytics

## Installation

1. Clone the repository to your local machine:
https://github.com/adisanduri/chatAppIdan.git

Replace `<repository_url>` with the URL of your repository.

2. Open the project in Android Studio.

3. Set up Firebase project and configure your project's Firebase credentials in the app.
- Create a new Firebase project on the Firebase console (https://console.firebase.google.com).
- Enable Google authentication in Firebase Authentication.
- Configure the project with your own Google Services file (`google-services.json`).
- Update the Firebase configuration in the app by replacing the `google-services.json` file in the `app` directory.

4. Build and run the application on an Android emulator or a physical device.

## Usage

1. On the login screen, click the "Login with Google" button to authenticate using your Google account.

2. After successful login, you will be directed to the chat screen.

3. In the chat screen, you can view messages from other users as well as your own messages.

4. To send a message, enter your message in the input field and click the send button.

5. Each message sent will trigger a push notification.

6. The chat screen will automatically scroll to the last received message.

## Dependencies

- Firebase: Used for authentication, database, and analytics.
- RecyclerView: Used for displaying messages in a scrollable list.
- Picasso: Used for loading and displaying profile pictures.

## Contributing

Contributions are welcome! If you find any issues or have suggestions for improvements, please open an issue or submit a pull request.

## License

This project is licensed under the [MIT License](LICENSE).
