# EVEFleetHistoryCompressor
Small project that allows to see how much everyone within your fleet made during the sessions history.

## TODO
	- Performance Improvement
		>>-> ...
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
		
		>>-> Change the way threads are handles. Joining and re-opening threads doesn't seem the best idea!
			  CPU usage gets from 0.1%< to 5.9%+- if you keep changes tables. Do a demo with one huge thread.
			=> Solution:
				Create one big thread that refreshes every x-seconds. Speed can be determined by the user depending
				on their device
			=> Issues:
				NONE
		
	- New Features
		>>-> ...