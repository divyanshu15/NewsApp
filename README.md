# NewsApp

An Android news reader that fetches Indian top headlines and category-specific stories from [News API](https://newsapi.org/). Articles appear in a tabbed interface with thumbnails and summaries; selecting a story opens its source URL in an in-app WebView.

## Features
- Six sections: Home, Science, Health, Sports, Entertainment, and Technology.
- Fetches up to 100 headlines for country code `in`.
- Displays title, description, author, publication timestamp, and thumbnail.
- Loads images with Glide and parses responses with Retrofit/Gson.
- Opens article URLs in an Android WebView.

## Technology
| Area | Implementation |
|---|---|
| Language / UI | Java, Android Views, Material Components, TabLayout, ViewPager, RecyclerView |
| Networking / JSON | Retrofit 2.9.0, Gson converter |
| Images | Glide 4.12.0 |
| Minimum Android | API 21 |
| Compile / target SDK | 30 / 30 |
| Build | Android Gradle Plugin 4.2.1, Gradle 6.7.1 |

## How it works
1. `MainActivity` creates the toolbar, six tabs, and ViewPager.
2. `PagerAdapter` maps the selected tab to its fragment.
3. Each category fragment requests headlines through `ApiUtilities.getApiInterface()`.
4. `ApiInterface` defines calls to `https://newsapi.org/v2/top-headlines`. Requests use country `in`; category fragments also send a category.
5. Retrofit/Gson maps the response into `mainNews` and `Modelclass`.
6. `Adapter` binds article data to RecyclerView rows; Glide loads thumbnails.
7. A card tap passes the article URL to `webView`.

## Main source files
- `MainActivity.java` — tab and ViewPager coordination.
- `PagerAdapter.java` — fragment selection.
- `Homefragment.java`, `Sciencefragment.java`, `Healthfragment.java`, `Sportsfragment.java`, `Entertainmentfragment.java`, `Technologyfragment.java` — category feeds.
- `ApiInterface.java`, `ApiUtilities.java` — API contract and Retrofit client.
- `mainNews.java`, `Modelclass.java` — response and article models.
- `Adapter.java` — RecyclerView rendering and article selection.
- `webView.java` — in-app article browser.

## Requirements and setup
- Android Studio, Android SDK, and a JDK compatible with the configured Gradle versions.
- A valid News API key and internet access.

1. Clone the repository and open the project root in Android Studio.
2. Allow Gradle sync to finish.
3. Obtain a key from [News API](https://newsapi.org/).
4. The current code embeds the key in Java source (including `MainActivity.java` and `Homefragment.java`). Replace it locally to test, but do not commit a real key. Prefer moving it to an untracked local configuration / BuildConfig field before sharing or releasing.
5. Run the `app` configuration on an emulator or device.

```bash
./gradlew assembleDebug
```

Windows: `gradlew.bat assembleDebug`. The debug APK is normally under `app/build/outputs/apk/debug/`.

## Configuration and limitations
- Internet permission is declared in the manifest.
- Treat the existing embedded API credential as exposed: rotate it in the provider dashboard and restrict it.
- This project uses an older Android Gradle Plugin, Gradle, SDK, and dependency set. A modern Android Studio installation may require a compatibility setup or planned build-system upgrade.
- Some network failure callbacks do not provide visible error feedback.
- This repository contains the Android client, not the News API service or a backend.
- No explicit license file was present when this README was prepared. Check licensing terms before redistribution.
