# My Favorite Things
This repository is a fork of Ivan Carballo's [archi][1] that shows off several features of [braque][2].

* The `Deserializer` and `Serializer` are used to aggregate data from Retrofit and a custom provider.
* The `Transformer` is used to compose types on the fly and send them off as fake Localytics events.
* The `Fanner` helps us aggregate the aggregated data into a Favorite list.
* The `StringProvisioner` helps transform deserialized data.

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
