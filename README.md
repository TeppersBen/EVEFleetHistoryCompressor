# EVEFleetHistoryCompressor
Small project that allows to see how much everyone within your fleet made during the sessions history.

## TODO
	- Performance Improvement
		>>-> Changing loot file shouldn't rebuild the whole table, but should use a stored table model.
		>>-> Change the way threads are handles. Joining and re-opening threads doesn't seem the best idea!
			  CPU usage gets from 0.1%< to 5.9%+- if you keep changes tables. Do a demo with one huge thread.
		
	- New Features
		>>-> JFX Frame, to make the application more appealing to the EVE players / Fleet Commanders.
		>>-> Table filter functions.