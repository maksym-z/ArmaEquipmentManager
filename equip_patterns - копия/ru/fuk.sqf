_unit = _this select 0;
[_unit] call compile preprocessFileLineNumbers 'equip_patterns\\fugk.sqf.sqf';
_unit addItem 'bar';
