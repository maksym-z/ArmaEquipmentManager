if (!isServer) exitWith {};
_unit = _this select 0;

removeAllWeapons _unit;
removeAllItems _unit;
removeAllAssignedItems _unit;
removeUniform _unit;
removeVest _unit;
removeBackpack _unit;
removeHeadgear _unit;
removeGoggles _unit;
for '_i' from 1 to 2 do {_unit addItemToUniform 'ACE_elasticBandage';};
for '_i' from 1 to 2 do {_unit addItemToUniform 'ACE_packingBandage';};
for '_i' from 1 to 2 do {_unit addItemToUniform 'ACE_quikclot';};
for '_i' from 1 to 2 do {_unit addItemToUniform 'ACE_fieldDressing';};
for '_i' from 1 to 2 do {_unit addItemToUniform 'ACE_tourniquet';};
for '_i' from 1 to 2 do {_unit addItemToUniform 'ACE_morphine';};
