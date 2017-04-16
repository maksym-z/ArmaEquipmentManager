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
_unit addItemToUniform 'ACE_MapTools';
_unit addWeapon 'rhs_weap_tr8';
_unit addMagazine ['rhs_30Rnd_545x39_AK_green', 2];
