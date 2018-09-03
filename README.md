# EVEFleetHistoryCompressor
Small project that allows to see how much everyone within your fleet made during the sessions history.

## TODO
	- Performance Improvement
		>>-> ...
		
	- New Features
		>>-> JFX Frame, to make the application more appealing to the EVE players / Fleet Commanders.
		>>-> Table filter functions.
		
## In progress
	- Performance Imporovement
		>>-> Changing loot file shouldn't rebuild the whole table, but should use a stored table model.
			=> Solution:
				data is stored within a repository object, later on used by the UI-Controller to translate that
				data-set into a table.
			=> Issues:
				Table won't refresh. Object->Table::setModel(model) should do the trick.
		
	- New Features
		>>-> ...