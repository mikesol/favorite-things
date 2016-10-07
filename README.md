# My Favorite Things
This repository is a fork of Ivan Carballo's [archi][1] that shows off several features of [braque][2].

* How [models][3] are defined in braque.
* How [apis][4] are defined in braque.
* The [`Deserializer`](/app/src/main/java/com/jongla/favoritethings/viewmodel/browse/BrowseFavoritesViewModel.java#L68-L92)
and [`Serializer`](/app/src/main/java/com/jongla/favoritethings/viewmodel/browse/BrowseThingViewModel.java#L86-L115) are used to aggregate data from Retrofit and a custom provider.
* The [`Transformer`](/app/src/main/java/com/jongla/favoritethings/viewmodel/ItemFavoriteThingViewModel.java#L73-L87) is used to compose types on the fly and send them off as fake Localytics events.
* The [`Fanner`](/app/src/main/java/com/jongla/favoritethings/Utils.java#L99-L119) helps us convert objects stored in the DB.
* The [`StringProvisioner`](/app/src/main/java/com/jongla/favoritethings/converter/BraqueConverterFactory.java#L34-L63) helps us map values in JSON to Braque objects.

### Libraries used 
* AppCompat, CardView and RecyclerView
* Data Binding
* RxJava & RxAndroid
* Retrofit 2

## License

```
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
[1]: https://github.com/ivacf/archi
[2]: https://github.com/jongla/braque
[3]: https://github.com/mikesol/favorite-things/tree/master/app/src/main/java/com/jongla/favoritethings/model/favoritething
[4]: https://github.com/mikesol/favorite-things/tree/master/app/src/main/java/com/jongla/favoritethings/api
