# QuotesApp

Simple quotes app, where you can discover quotes, filter them and save favorites.

The quotes are from HealThruWords through RapidAPI by using Retrofit with Moshi JSON library and OkHttp. Also it has a repository for saving favorite quotes and caching the quotes received from the API in order to fast load the quotes list. 

Discover quotes page is using an infinite scroller for the RecyclerView to continuously receive new quotes. Also user can filter the quotes by category by pressing a FloatingActionButton which opens a BottomSheetDialogFragment where the category can be selected.

Furthermore, the user can see a bigger version of the quote in a separate page, by clicking a quote from the list. There, the user has the possibility to add the current viewed quote to Favorites and see all saved favorites inside the Favorites page reached by pressing Favorites button from the bottom navigation bar.
