<?php

namespace Api\Transformers;

use App\User;
use League\Fractal\TransformerAbstract;

class UserTransformer extends TransformerAbstract
{
	public function transform(User $user)
	{
		return [
			'id' 	=> (int) $user->id,
			'cid'	=> (int) $user->cid,
			'name'  => $user->name,
			'profile_image' => $user->profile_image
		];
	}
}