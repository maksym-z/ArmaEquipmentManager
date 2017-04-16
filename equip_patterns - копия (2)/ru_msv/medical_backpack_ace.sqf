if (!isServer) exitWith {};
_unit = _this select 0;

_unit addBackpack 'rhs_assault_umbts';
_unit linkItem 'ItemMap';
_unit linkItem 'ItemRadio';
for '_i' from 1 to 25 do {_unit addItemToBackpack 'ACE_fieldDressing';};
for '_i' from 1 to 15 do {_unit addItemToBackpack 'ACE_morphine';};
for '_i' from 1 to 15 do {_unit addItemToBackpack 'ACE_Epinephrine';};
for '_i' from 1 to 6 do {_unit addItemToBackpack 'ACE_salineIV';};
for '_i' from 1 to 6 do {_unit addItemToBackpack 'ACE_tourniquet';};
for '_i' from 1 to 6 do {_unit addItemToBackpack 'ACE_quikclot';};
