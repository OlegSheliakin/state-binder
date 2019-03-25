# StateBinder

[ ![Download](https://api.bintray.com/packages/olegsheliakin/maven/statebinder/images/download.svg) ](https://bintray.com/olegsheliakin/maven/statebinder/_latestVersion)

StateBinder is a tiny library for view state management. 

If you use the MVI pattern or any other pattern using the concept of states to develop your applications, you may have encountered the problem of frequently updating widgets, which leads to poor performance especially you perform frequent screen state updates. 
StateBinder eliminates redundant view rendering when the state changes.

# Download
~~~ groovy
dependencies {
  impelentation 'com.olegsheliakin:statebinder:latest'
}
~~~

# How to use?

1. Create State class for View:

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
   
}
~~~

3. Bind state's properties to actions:

~~~ kotlin
 stateBinder.apply {
 
            bind(MainState::label) {
                tvLabel.text = it
            }
            
            bindNullable(MainState::errorText) {
                etText.error = it
            }
            
        }
~~~

  Actions will be called only when the state changes.

4. Update state by calling:

~~~ kotlin
stateBinder.newState(newState)
~~~

Full code:
~~~ koltin
class MainFragment : Fragment() {

    private val viewModel: MainViewModel by lazy {
        return@lazy ViewModelProviders.of(this@MainFragment)[MainViewModel::class.java]
    }

    private val stateBinder: StateBinder<MainState> = StateBinder.create()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.state.observe(viewLifecycleOwner, Observer {
            it?.let(stateBinder::newState)
        })

        stateBinder.apply {
            applyCurrentState()
            bind(MainState::label) {
                tvLabel.text = it
            }
            bindNullable(MainState::errorText) {
                etText.error = it
            }
        }
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

