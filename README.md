# 📇 Contact Management App

## 📌 Overview
An Android-based contact management app that allows users to add, view, update, and delete contacts. It features an SQLite database for storage and a user-friendly interface with fragment-based navigation.

## 🔹 Features
- 📂 **Add Contacts** – Input name, phone, and birthdate.
- 📋 **View Contacts** – List all saved contacts.
- ✏️ **Update Contacts** – Modify existing contact details.
- 🗑 **Delete Contacts** – Remove unwanted contacts.
- 🔍 **Search Contacts** – Quickly find saved contacts.

## 🔧 Components
- **DatabaseManager**: Handles SQLite operations.
- **MainActivity**: Manages the navigation and UI.
- **Fragments**: 
  - `userFragment` – Add new contacts.
  - `allFragment` – Display all contacts.
  - `renewFragment` – Update or delete contacts.
- **AddActivity**: Provides an interface for contact addition.

## 🚀 Usage
- Navigate through the app using bottom navigation.
- Add new contacts in the `userFragment`.
- View all contacts in `allFragment`.
- Update or delete contacts in `renewFragment`.
