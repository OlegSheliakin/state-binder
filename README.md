# StateBinder

[ ![Download](https://api.bintray.com/packages/olegsheliakin/maven/statebinder/images/download.svg) ](https://bintray.com/olegsheliakin/maven/statebinder/_latestVersion)

StateBinder is a tiny library for view state management. It eliminates redundant view rendering when the state changes.

# Download
~~~ groovy
dependencies {
  impelentation 'com.olegsheliakin:statebinder:latest'
}
~~~

# How to use?

1. Create you State class for your view:

~~~ kotlin
data class MainState(
    val label: String,
    val errorText: String?
) : State
~~~

2. Create StateBinder:

~~~ kotlin
class MainFragment : Fragment() {

    private val stateBinder: StateBinder<MainState> = StateBinder.create()
    
    ...
}
~~~

# License
```
The MIT License (MIT)
=====================

Copyright © 2019 Oleg Sheliakin

Permission is hereby granted, free of charge, to any person
obtaining a copy of this software and associated documentation
files (the “Software”), to deal in the Software without
restriction, including without limitation the rights to use,
copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the
Software is furnished to do so, subject to the following
conditions:

The above copyright notice and this permission notice shall be
included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND,
EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
OTHER DEALINGS IN THE SOFTWARE.
```

