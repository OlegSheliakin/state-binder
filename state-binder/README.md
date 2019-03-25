# StateHolder

[Sample](https://gitlab.smedialink.com/Android/Sample/stateholder)

Удобно использовать при использовании State для экранов cocтояний(что это и зачем можно посмотреть здесь [Hannes Dorfman MVI](http://hannesdorfmann.com/android/mosby3-mvi-1)).

Помогает удобным образом забиндить свойства.

Обновляет только те свойства, которые были изменены. Старые значения не применяются. Аналог DiffUtil для RecyclerView.

Пример использования. 

```
class MainFragment : Fragment() {

    private val viewModel: MainViewModel by lazy {
        return@lazy ViewModelProviders.of(this@MainFragment)[MainViewModel::class.java]
    }

    private val stateHolder: StateHolder<MainState> = StateHolder.create()
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        stateHolder.onViewCreated()

        btnSetText.setOnClickListener {
            viewModel.loadText()
        }

		//биндим свойства к Action
		//при изменении свойства будет выполнен Action
        stateHolder.bind(MainState::label) {
            tvLabel.text = it
        }
        
		//слушаем вью модель и пробрасываем State в stateHolder.newState(State)
		//StateHolder сохранит новый State и выполнит Action для тех свйости, которые были изменены
        viewModel.state.observe(viewLifecycleOwner, Observer {
            stateHolder.newState(it!!)
        })
    }
```

